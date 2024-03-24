package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Cat;
import service.CatService;
import service.IService;
import utils.InputHandler;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Component
public class AddNewCommandToCatAction implements IAction {
    @InjectByInterface(clazz = CatService.class)
    private IService<Cat> catService;


    @Override
    public void execute() {
        try {
            Cat cat = InputHandler.getAnimalByInput(catService);
            System.out.println("\nВведите команду, которой надо обучить кота: ");
            String command = InputHandler.getUserInput();
            catService.addNewCommand(cat, command);
            System.out.println("\nУдалось обновить список команд кота");
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
