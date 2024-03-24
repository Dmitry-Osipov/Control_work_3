package dao;

import annotation.annotations.Component;
import annotation.annotations.ConfigProperty;
import entity.Identifiable;
import utils.exceptions.ErrorMessages;
import utils.exceptions.NoEntityException;
import utils.exceptions.TechnicalException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Реализация интерфейса {@link IDao}, предоставляющая методы доступа к базе данных. Класс выполняет операции
 * сохранения, обновления, удаления и получения объектов из базы данных. Для взаимодействия с базой данных используется
 * JDBC.
 */
@Component
public class JdbcDao implements IDao {
    @ConfigProperty(propertyKey = "db_url")
    private String dbUrl;
    @ConfigProperty(propertyKey = "db_user")
    private String dbUser;
    @ConfigProperty(propertyKey = "db_password")
    private String dbPassword;

    /**
     * Сохраняет указанный объект в базе данных.
     * @param essence объект, который нужно сохранить в базе данных.
     * @throws SQLException если произошла ошибка SQL.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     * @throws TechnicalException если не удалось вставить данные в БД.
     */
    @Override
    public <T extends Identifiable> void save(T essence) throws SQLException, IllegalAccessException {
        String sql = sqlInsertIntoBuild(essence);
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            prepareStatement(essence, statement);

            if (statement.executeUpdate() == 0) {
                throw new TechnicalException(ErrorMessages.NOT_INSERT_DATA_TO_DB.getMessage());
            }
        }
    }

    /**
     * Обновляет указанный объект в базе данных.
     * @param essence объект, который нужно обновить в базе данных.
     * @throws SQLException если произошла ошибка SQL.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     * @throws TechnicalException если не удалось обновить данные в БД.
     */
    @Override
    public <T extends Identifiable> void update(T essence) throws IllegalAccessException, SQLException {
        String sql = sqlUpdateBuild(essence);
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            prepareStatement(essence, statement);

            if (statement.executeUpdate() == 0) {
                throw new TechnicalException(ErrorMessages.NOT_UPDATE_DATA_TO_DB.getMessage());
            }
        }
    }

    /**
     * Возвращает один объект из базы данных по его идентификатору.
     * @param id идентификатор объекта.
     * @param clazz класс объекта.
     * @return объект из базы данных.
     * @throws SQLException если произошла ошибка SQL.
     * @throws NoSuchMethodException если запрашиваемый метод не существует.
     * @throws InvocationTargetException если вызванный метод вызывает исключение.
     * @throws InstantiationException если попытка создать экземпляр класса абстрактного класса, интерфейса, массива
     * абстрактных классов или интерфейсов, или если класс, указанный в параметре типа, является абстрактным.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     * @throws NoSuchFieldException если запрашиваемое поле не существует.
     * @throws NoEntityException если сущность с переданным id отсутствует в БД.
     */
    @Override
    public <T extends Identifiable> T getOne(int id, Class<T> clazz) throws SQLException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        String table = parseStringFromCamelCaseToSnakeCase(clazz.getSimpleName());
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE id = %d", table, id))
        ) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            T entity = clazz.getDeclaredConstructor().newInstance();

            if (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = parseStringFromSnakeCaseToCamelCase(metaData.getColumnName(i));
                    Object columnValue = parseValueFromDb(columnName, resultSet.getObject(i));

                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(entity, columnValue);
                }
            } else {
                throw new NoEntityException(ErrorMessages.NO_ENTITY_EXCEPTION.getMessage() + " в БД");
            }

            return entity;
        }
    }

    /**
     * Возвращает список всех объектов указанного класса из базы данных.
     * @param clazz класс объектов.
     * @return список объектов из базы данных.
     * @throws SQLException если произошла ошибка SQL.
     * @throws NoSuchFieldException если запрашиваемое поле не существует.
     * @throws InvocationTargetException если вызванный метод вызывает исключение.
     * @throws NoSuchMethodException если запрашиваемый метод не существует.
     * @throws InstantiationException если попытка создать экземпляр класса абстрактного класса, интерфейса, массива
     * абстрактных классов или интерфейсов, или если класс, указанный в параметре типа, является абстрактным.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     */
    @Override
    public <T extends Identifiable> List<T> getAll(Class<T> clazz) throws SQLException, NoSuchFieldException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        String table = parseStringFromCamelCaseToSnakeCase(clazz.getSimpleName());
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", table))
        ) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                while (resultSet.next()) {
                    list.add(getOne((Integer) resultSet.getObject(i), clazz));
                }
            }
        }

        return list;
    }

    /**
     * Служебный метод позволяет снизить дублирование кода. Метод проводит подготовку {@link PreparedStatement} для
     * передачи данных дочерних классов {@link entity.Animal} в БД.
     * @param essence сущность, которую требуется сохранить или обновить в БД.
     * @param statement объект, описывающий подготовку компилированного sql-стейтмента.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     * @throws SQLException если произошла ошибка SQL..
     */
    private <T extends Identifiable> void prepareStatement(T essence, PreparedStatement statement)
            throws IllegalAccessException, SQLException {
        Field[] fields = essence.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value;
            if (fields[i].getName().equals("commands")) {
                value = String.join(", ", (List<String>) fields[i].get(essence));
            } else {
                value = fields[i].get(essence);
            }

            statement.setObject(i + 1, value);
        }
    }

    /**
     * Преобразует строку из формата camelCase в snake_case.
     * @param str строка в формате camelCase.
     * @return строка в формате snake_case.
     */
    private String parseStringFromCamelCaseToSnakeCase(String str) {
        StringBuilder snakeCase = new StringBuilder();
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && Character.isUpperCase(chars[i])) {
                snakeCase.append(Character.toLowerCase(chars[i]));
            } else if (Character.isUpperCase(chars[i])) {
                snakeCase.append("_").append(Character.toLowerCase(chars[i]));
            } else {
                snakeCase.append(chars[i]);
            }
        }

        return snakeCase.toString();
    }

    /**
     * Преобразует строку из формата camelCase в snake_case.
     * @param str строка в формате camelCase.
     * @return строка в формате snake_case.
     */
    private String parseStringFromSnakeCaseToCamelCase(String str) {
        String[] parts = str.split("_");
        StringBuilder camelCase = new StringBuilder(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            camelCase.append(Character.toUpperCase(parts[i].charAt(0)))
                    .append(parts[i].substring(1));
        }

        return camelCase.toString();
    }

    /**
     * Преобразует значение из базы данных в соответствующий тип Java.
     * @param columnName имя колонки в базе данных.
     * @param value значение, которое требуется передать.
     * @return значение из базы данных в соответствующем типе Java.
     */
    private Object parseValueFromDb(String columnName, Object value) {
        Object columnValue;
        if (columnName.contains("Date")) {
            columnValue = parseDateFromDb(value);
        } else if (columnName.equals("commands")) {
            columnValue = Arrays.stream(((String) value).split(", ")).toList();
        } else {
            columnValue = value;
        }

        return columnValue;
    }

    /**
     * Преобразует значение даты из базы данных в тип LocalDate.
     * @param date объект даты из БД.
     * @return значение времени из базы данных в типе LocalDate.
     */
    private Object parseDateFromDb(Object date) {
        Object columnValue = date;
        if (columnValue != null) {
            columnValue = ((Date) columnValue).toLocalDate();
        }

        return columnValue;
    }

    /**
     * Создает SQL запрос для вставки данных в таблицу.
     * @param entity объект, данные которого нужно вставить в таблицу.
     * @return SQL запрос для вставки данных в таблицу.
     */
    private <T extends Identifiable> String sqlInsertIntoBuild(T entity) {
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql =
                new StringBuilder("INSERT INTO " + parseStringFromCamelCaseToSnakeCase(clazz.getSimpleName()) + " (");

        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                sql.append(", ");
            }
            sql.append(parseStringFromCamelCaseToSnakeCase(fields[i].getName()));
        }

        sql.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                sql.append(", ");
            }
            sql.append("?");
        }
        sql.append(")");

        return sql.toString();
    }

    /**
     * Создает SQL запрос для обновления данных в таблице.
     * @param entity объект, данные которого нужно обновить в таблице.
     * @return SQL запрос для обновления данных в таблице.
     */
    private <T extends Identifiable> String sqlUpdateBuild(T entity) {
        Class<?> clazz = entity.getClass();
        StringBuilder sql =
                new StringBuilder("UPDATE " + parseStringFromCamelCaseToSnakeCase(clazz.getSimpleName()) + " SET ");

        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                sql.append(", ");
            }
            sql.append(parseStringFromCamelCaseToSnakeCase(fields[i].getName())).append(" = ?");
        }
        sql.append(" WHERE id = ").append(entity.getId());

        return sql.toString();
    }
}
