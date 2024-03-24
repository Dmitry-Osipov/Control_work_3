package ui;

import annotation.annotations.Autowired;
import annotation.annotations.Component;
import annotation.factory.InitializeComponent;
import utils.exceptions.ErrorMessages;

/**
 * Класс отвечает за навигацию по меню.
 */
@Component
public class Navigator implements InitializeComponent {
    private Menu currentMenu;
    private Menu rootMenu;
    @Autowired
    private Builder builder;

    /**
     * Метод проводит инициализацию главного и текущего меню.
     */
    @Override
    public void init() {
        currentMenu = builder.getRootMenu();
        rootMenu = currentMenu;
    }

    /**
     * Метод выводит меню.
     */
    public void printMenu() {
        System.out.println("\nМеню: " + currentMenu.getName());
        for (int i = 0; i < currentMenu.getMenuItems().length; i++) {
            System.out.println(i + 1 + ". " + currentMenu.getMenuItems()[i].title());
        }
    }

    /**
     * Метод производит навигацию по меню.
     * @param index Индекс меню/действия, к которому произойдёт переход.
     */
    public void navigate(Integer index) {
        if (index < 0 || currentMenu.getMenuItems().length <= index) {
            System.out.println("\n" + ErrorMessages.INCORRECT_INPUT.getMessage());
        } else {
            Menu nextMenu = getNextMenu(index);
            currentMenu.getMenuItems()[index].doAction();
            currentMenu = nextMenu;
        }
    }

    /**
     * Служебный метод проводит проверку наличия следующего меню.
     * @param index Индекс.
     * @return Следующее меню, если оно есть, иначе главное меню.
     */
    private Menu getNextMenu(Integer index) {
        if (currentMenu.getMenuItems()[index].nextMenu() != null) {
            return currentMenu.getMenuItems()[index].nextMenu();
        }

        return rootMenu;
    }
}
