package utils;

import entity.Identifiable;
import service.CamelService;
import service.CatService;
import service.DogService;
import service.DonkeyService;
import service.HamsterService;
import service.HorsesService;
import service.IService;
import utils.InputHandler;
import utils.exceptions.ErrorMessages;
import utils.exceptions.TechnicalException;
import utils.file.DataPath;
import utils.file.IdFileManager;
import utils.validators.StringToLocalDateValidator;
import utils.validators.UniqueIdValidator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Вспомогательный класс для экшенов по работе с созданием животных.
 */
public final class AnimalOperationHelper {
    private AnimalOperationHelper() {
    }

    /**
     * Метод получения id животного.
     * @param service сервис, обрабатывающий конкретный тип животного.
     * @return id животного.
     * @throws SQLException если произошла ошибка SQL.
     * @throws NoSuchFieldException если запрашиваемое поле не существует.
     * @throws InvocationTargetException если вызванный метод вызывает исключение.
     * @throws NoSuchMethodException если запрашиваемый метод не существует.
     * @throws InstantiationException если попытка создать экземпляр класса абстрактного класса, интерфейса, массива
     * абстрактных классов или интерфейсов, или если класс, указанный в параметре типа, является абстрактным.
     * @throws IllegalAccessException если доступ к классу или его полям был закрыт.
     * @throws IOException низкоуровневая ошибка ввода/вывода.
     */
    public static <T extends Identifiable> int getId(IService<T> service) throws SQLException, NoSuchFieldException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException,
            IOException {
        String path = DataPath.ID_DIRECTORY.getPath();
        switch (service) {
            case CamelService ignored -> path += "camel_id.txt";
            case CatService ignored -> path += "cat_id.txt";
            case DogService ignored -> path += "dog_id.txt";
            case DonkeyService ignored -> path += "donkey_id.txt";
            case HamsterService ignored -> path += "hamster_id.txt";
            case HorsesService ignored -> path += "horse_id.txt";
            case null, default -> throw new TechnicalException("Нет специального обработчика для данного класса");
        }

        int id = IdFileManager.readMaxId(path);
        if (!UniqueIdValidator.validateUniaueId(service.getAllAnimals(), id)) {
            id = service.getAllAnimals().stream().mapToInt(Identifiable::getId).max().orElse(0) + 1;
        }
        IdFileManager.writeMaxId(path, id + 1);

        return id;
    }

    /**
     * Метод получения имени животного.
     * @return имя животного.
     */
    public static String getName() {
        String name = InputHandler.getUserInput();
        while (name.isEmpty()) {
            System.out.println("\n" + ErrorMessages.INCORRECT_INPUT.getMessage());
            name = InputHandler.getUserInput();
        }

        return name;
    }

    /**
     * Метод получения даты рождения животного.
     * @return Дата рождения животного.
     */
    public static LocalDate getBirthDate() {
        String date = InputHandler.getUserInput();
        while (!StringToLocalDateValidator.validateLocalDate(date)) {
            System.out.println("\n" + ErrorMessages.INCORRECT_INPUT.getMessage());
        }
        String[] dates = date.split("-");

        return LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
    }

    /**
     * Метод получения команд животного.
     * @return Команды животного.
     */
    public static List<String> getCommands() {
        String commands = InputHandler.getUserInput();
        List<String> list = List.of(commands.split(", "));
        while (list.isEmpty()) {
            System.out.println("\n" + ErrorMessages.INCORRECT_INPUT.getMessage());
            commands = InputHandler.getUserInput();
            list = List.of(commands.split(", "));
        }

        return list;
    }
}
