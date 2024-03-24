package ui;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import annotation.factory.InitializeComponent;
import lombok.Getter;
import ui.actions.AddCamelAction;
import ui.actions.AddCatAction;
import ui.actions.AddDogAction;
import ui.actions.AddDonkeyAction;
import ui.actions.AddHamsterAction;
import ui.actions.AddHorseAction;
import ui.actions.AddNewCommandToCamelAction;
import ui.actions.AddNewCommandToCatAction;
import ui.actions.AddNewCommandToDogAction;
import ui.actions.AddNewCommandToDonkeyAction;
import ui.actions.AddNewCommandToHamsterAction;
import ui.actions.AddNewCommandToHorseAction;
import ui.actions.CountAllAnimalsAction;
import ui.actions.IAction;
import ui.actions.ShowAllCamelsAction;
import ui.actions.ShowAllCatsAction;
import ui.actions.ShowAllDogsAction;
import ui.actions.ShowAllDonkeysAction;
import ui.actions.ShowAllHamstersAction;
import ui.actions.ShowAllHorsesAction;
import ui.actions.ShowCamelCommandsAction;
import ui.actions.ShowCatCommandsAction;
import ui.actions.ShowDogCommandsAction;
import ui.actions.ShowDonkeyCommandsAction;
import ui.actions.ShowHamsterCommandsAction;
import ui.actions.ShowHorseCommandsAction;

/**
 * Класс отвечает за формирование меню.
 */
@Component
public class Builder implements InitializeComponent {
    @Getter
    private Menu rootMenu;
    @InjectByInterface(clazz = AddCamelAction.class)
    private IAction addCamelAction;
    @InjectByInterface(clazz = ShowAllCamelsAction.class)
    private IAction showAllCamelsAction;
    @InjectByInterface(clazz = ShowCamelCommandsAction.class)
    private IAction showCamelCommandsAction;
    @InjectByInterface(clazz = AddNewCommandToCamelAction.class)
    private IAction addNewCommandToCamelAction;
    @InjectByInterface(clazz = AddCatAction.class)
    private IAction addCatAction;
    @InjectByInterface(clazz = ShowAllCatsAction.class)
    private IAction showAllCatsAction;
    @InjectByInterface(clazz = ShowCatCommandsAction.class)
    private IAction showCatCommandsAction;
    @InjectByInterface(clazz = AddNewCommandToCatAction.class)
    private IAction addNewCommandToCatAction;
    @InjectByInterface(clazz = AddDogAction.class)
    private IAction addDogAction;
    @InjectByInterface(clazz = ShowAllDogsAction.class)
    private IAction showAllDogsAction;
    @InjectByInterface(clazz = ShowDogCommandsAction.class)
    private IAction showDogCommandsAction;
    @InjectByInterface(clazz = AddNewCommandToDogAction.class)
    private IAction addNewCommandToDogAction;
    @InjectByInterface(clazz = AddDonkeyAction.class)
    private IAction addDonkeyAction;
    @InjectByInterface(clazz = ShowAllDonkeysAction.class)
    private IAction showAllDonkeysAction;
    @InjectByInterface(clazz = ShowDonkeyCommandsAction.class)
    private IAction showDonkeyCommandsAction;
    @InjectByInterface(clazz = AddNewCommandToDonkeyAction.class)
    private IAction addNewCommandToDonkeyAction;
    @InjectByInterface(clazz = AddHamsterAction.class)
    private IAction addHamsterAction;
    @InjectByInterface(clazz = ShowAllHamstersAction.class)
    private IAction showAllHamstersAction;
    @InjectByInterface(clazz = ShowHamsterCommandsAction.class)
    private IAction showHamsterCommandsAction;
    @InjectByInterface(clazz = AddNewCommandToHamsterAction.class)
    private IAction addNewCommandToHamsterAction;
    @InjectByInterface(clazz = AddHorseAction.class)
    private IAction addHorseAction;
    @InjectByInterface(clazz = ShowAllHorsesAction.class)
    private IAction showAllHorsesAction;
    @InjectByInterface(clazz = ShowHorseCommandsAction.class)
    private IAction showHorseCommandAction;
    @InjectByInterface(clazz = AddNewCommandToHorseAction.class)
    private IAction addNewCommandToHorseAction;
    @InjectByInterface(clazz = CountAllAnimalsAction.class)
    private IAction countAnimals;

    /**
     * Метод проводит настройку главного меню.
     */
    @Override
    public void init() {
        rootMenu = buildMainMenu();
    }

    /**
     * Служебный метод предназначен для формирования главного меню.
     * @return Главное меню.
     */
    private Menu buildMainMenu() {
        MenuItem camelsMenu = new MenuItem("Управление верблюдами", null, buildCamelsMenu());
        MenuItem catsMenu = new MenuItem("Управление котами", null, buildCatsMenu());
        MenuItem dogsMenu = new MenuItem("Управление собаками", null, buildDogsMenu());
        MenuItem donkeysMenu = new MenuItem("Управление ослами", null, buildDonkeysMenu());
        MenuItem hamstersMenu = new MenuItem("Управление хомяками", null, buildHamstersMenu());
        MenuItem horsesMenu = new MenuItem("Управление лошадьми", null, buildHorsesMenu());
        MenuItem countTotalAnimals = new MenuItem("Общее количество всех животных", countAnimals, null);

        return new Menu("Главное меню", new MenuItem[]{camelsMenu, catsMenu, dogsMenu, donkeysMenu, hamstersMenu,
                horsesMenu, countTotalAnimals});
    }

    /**
     * Служебный метод предназначен для формирования меню управления верблюдами.
     * @return Меню управления верблюдами.
     */
    private Menu buildCamelsMenu() {
        MenuItem addCamel = new MenuItem("Добавить верблюда", addCamelAction, null);
        MenuItem showCamels = new MenuItem("Вывести список верблюдов по возрастанию даты рождения",
                showAllCamelsAction, null);
        MenuItem showCommands = new MenuItem("Вывести список команд верблюда",
                showCamelCommandsAction, null);
        MenuItem addNewCommand = new MenuItem("Добавить команду верблюду",
                addNewCommandToCamelAction, null);

        return new Menu("Управление верблюдами", new MenuItem[]{addCamel, showCamels, showCommands,
                addNewCommand});
    }

    /**
     * Служебный метод предназначен для формирования меню управления котами.
     * @return Меню управления котами.
     */
    private Menu buildCatsMenu() {
        MenuItem addCat = new MenuItem("Добавить кота", addCatAction, null);
        MenuItem showCats = new MenuItem("Вывести список котов по возрастанию даты рождения",
                showAllCatsAction, null);
        MenuItem showCommands = new MenuItem("Вывести список команд кота", showCatCommandsAction, null);
        MenuItem addNewCommand = new MenuItem("Добавить команду коту", addNewCommandToCatAction, null);

        return new Menu("Управление котами", new MenuItem[]{addCat, showCats, showCommands, addNewCommand});
    }

    /**
     * Служебный метод предназначен для формирования меню управления собаками.
     * @return Меню управления собаками.
     */
    private Menu buildDogsMenu() {
        MenuItem addDog = new MenuItem("Добавить собаку", addDogAction, null);
        MenuItem showDogs = new MenuItem("Вывести список собак по возрастанию даты рождения",
                showAllDogsAction, null);
        MenuItem showCommands = new MenuItem("Вывести список команд собаки", showDogCommandsAction, null);
        MenuItem addNewCommand = new MenuItem("Добавить команду собаке", addNewCommandToDogAction, null);

        return new Menu("Управление собаками", new MenuItem[]{addDog, showDogs, showCommands, addNewCommand});
    }

    /**
     * Служебный метод предназначен для формирования меню управления ослами.
     * @return Меню управления ослами.
     */
    private Menu buildDonkeysMenu() {
        MenuItem addDonkey = new MenuItem("Добавить осла", addDonkeyAction, null);
        MenuItem showDonkeys = new MenuItem("Вывести список ослов по возрастанию даты рождения",
                showAllDonkeysAction, null);
        MenuItem showCommands = new MenuItem("Вывести список команд осла",
                showDonkeyCommandsAction, null);
        MenuItem addNewCommand = new MenuItem("Добавить команду ослу", addNewCommandToDonkeyAction, null);

        return new Menu("Управление ослами", new MenuItem[]{addDonkey, showDonkeys, showCommands, addNewCommand});
    }

    /**
     * Служебный метод предназначен для формирования меню управления хомяками.
     * @return Меню управления хомяками.
     */
    private Menu buildHamstersMenu() {
        MenuItem addHamster = new MenuItem("Добавить хомяка", addHamsterAction, null);
        MenuItem showHamsters = new MenuItem("Вывести список хомяков по возрастанию даты рождения",
                showAllHamstersAction, null);
        MenuItem showCommands = new MenuItem("Вывести список команд хомяка",
                showHamsterCommandsAction, null);
        MenuItem addNewCommand = new MenuItem("Добавить команду хомяку",
                addNewCommandToHamsterAction, null);

        return new Menu("Управление хомяками", new MenuItem[]{addHamster, showHamsters, showCommands,
                addNewCommand});
    }

    /**
     * Служебный метод предназначен для формирования меню управления лошадьми.
     * @return Меню управления лошадьми.
     */
    private Menu buildHorsesMenu() {
        MenuItem addHorse = new MenuItem("Добавить лошадь", addHorseAction, null);
        MenuItem showHorses = new MenuItem("Вывести список лошадей по возрастанию даты рождения",
                showAllHorsesAction, null);
        MenuItem showCommands = new MenuItem("Вывести список команд лошади",
                showHorseCommandAction, null);
        MenuItem addNewCommand = new MenuItem("Добавить команду лошади",
                addNewCommandToHorseAction, null);

        return new Menu("Управление лошадьми", new MenuItem[]{addHorse, showHorses, showCommands, addNewCommand});
    }
}
