package service;

import entity.Identifiable;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс-прослойка между логикой приложения и логикой работы с данными.
 */
public interface IService <T extends Identifiable> {
    /**
     * Сохраняет указанный объект в базе данных.
     * @param entity объект, который нужно сохранить в базе данных.
     * @throws SQLException если произошла ошибка SQL.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     */
    void add(T entity) throws SQLException, IllegalAccessException;

    /**
     * Возвращает список всех команд объекта.
     * @param id id сущности.
     * @return список команд объектов.
     * @throws SQLException если произошла ошибка SQL.
     * @throws NoSuchFieldException если запрашиваемое поле не существует.
     * @throws InvocationTargetException если вызванный метод вызывает исключение.
     * @throws NoSuchMethodException если запрашиваемый метод не существует.
     * @throws InstantiationException если попытка создать экземпляр класса абстрактного класса, интерфейса, массива
     * абстрактных классов или интерфейсов, или если класс, указанный в параметре типа, является абстрактным.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     */
    List<String> getAllCommands(int id) throws SQLException, NoSuchFieldException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * Сохраняет указанную команду для объекта.
     * @param entity объект, которому требуется передать новую команду.
     * @param command команда, которую требуется сохранить
     * @throws SQLException если произошла ошибка SQL.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     */
    void addNewCommand(T entity, String command) throws SQLException, IllegalAccessException;

    /**
     * Возвращает список всех объектов из базы данных.
     * @return список объектов из базы данных.
     * @throws SQLException если произошла ошибка SQL.
     * @throws NoSuchFieldException если запрашиваемое поле не существует.
     * @throws InvocationTargetException если вызванный метод вызывает исключение.
     * @throws NoSuchMethodException если запрашиваемый метод не существует.
     * @throws InstantiationException если попытка создать экземпляр класса абстрактного класса, интерфейса, массива
     * абстрактных классов или интерфейсов, или если класс, указанный в параметре типа, является абстрактным.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     */
    List<T> getAllAnimals() throws SQLException, NoSuchFieldException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * Возвращает число объектов из базы данных.
     * @return число объектов из базы данных.
     * @throws SQLException если произошла ошибка SQL.
     * @throws NoSuchFieldException если запрашиваемое поле не существует.
     * @throws InvocationTargetException если вызванный метод вызывает исключение.
     * @throws NoSuchMethodException если запрашиваемый метод не существует.
     * @throws InstantiationException если попытка создать экземпляр класса абстрактного класса, интерфейса, массива
     * абстрактных классов или интерфейсов, или если класс, указанный в параметре типа, является абстрактным.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     */
    int getTotalAnimals() throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException;
}
