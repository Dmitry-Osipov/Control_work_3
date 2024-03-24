package ui;

import annotation.annotations.Autowired;
import annotation.annotations.Component;
import utils.exceptions.ErrorMessages;

import java.util.Scanner;

/**
 * Класс отвечает за работу UI.
 */
@Component
public class MenuController {
    @Autowired
    private Navigator navigator;

    /**
     * Метод запускает приложение отеля.
     */
    public void run() {
        while (true) {

            navigator.printMenu();
            int choice = getUserInput() - 1;
            if (choice == -2) {
                exit();
                break;
            }
            navigator.navigate(choice);
        }
    }

    /**
     * Служебный метод запрашивает у пользователя число, проверяет валидность поступивших данных от пользователя.
     * @return Число.
     */
    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите действие (для выхода введите -1): ");
        while (!scanner.hasNextInt()) {
            System.out.println("\n" + ErrorMessages.INCORRECT_INPUT.getMessage());
            scanner.next();
        }

        return scanner.nextInt();
    }

    /**
     * Служебный метод производит user-friendly выход из программы.
     */
    private void exit() {
        System.out.println("\nВыход из программы...");
    }
}
