package utils;

import entity.Identifiable;
import service.IService;
import utils.exceptions.ErrorMessages;
import utils.exceptions.NoEntityException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс является финальным и предоставляет статические методы для обработки ввода пользователя.
 */
public final class InputHandler {
    private InputHandler() {
    }

    /**
     * Метод возвращает строку, введённую пользователем.
     * @return Полная строка, введённая пользователем.
     */
    public static String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Метод запрашивает у пользователя число, проверяя число на валидность.
     * @return Первое число, которое введёт пользователь.
     */
    public static int getUserIntegerInput() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("\n" + ErrorMessages.INCORRECT_INPUT.getMessage());
            scanner.next();
        }

        return scanner.nextInt();
    }

    /**
     * Метод выводит в консоль все животные. Пользователю требуется выбрать кого-либо из животных.
     * @return Животное.
     * @throws NoEntityException Ошибка связана с отсутствием животного.
     */
    public static <T extends Identifiable> T getAnimalByInput(IService<T> service) throws SQLException,
            NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        List<T> list = service.getAllAnimals();
        if (list.isEmpty()) {
            throw new NoEntityException(ErrorMessages.NO_ENTITY_EXCEPTION.getMessage());
        }

        System.out.println("\nВыберите животное: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        int choice = getEntityByInput(list, false);

        return list.get(choice);
    }

    /**
     * Служебный метод предназначен для снижения дублирования кода. Метод получает животного от
     * пользователя.
     * @param list Список животных.
     * @param manyEntity {@code true}, если нужно получить несколько сущностей. {@code false}, если нужно получить одну
     * сущность.
     * @return Выбор пользователя.
     */
    private static <T extends Identifiable> int getEntityByInput(List<T> list, boolean manyEntity) {
        int choice = getUserIntegerInput() - 1;
        while (choice < 0 || list.size() < choice) {
            if (manyEntity && choice == -2) {
                break;
            }

            System.out.println("\n" + ErrorMessages.INCORRECT_INPUT.getMessage());
            choice = getUserIntegerInput() - 1;
        }

        return choice;
    }
}
