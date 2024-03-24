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
public class ShowCatCommandsAction implements IAction {
    @InjectByInterface(clazz = CatService.class)
    private IService<Cat> catService;

    @Override
    public void execute() {
        try {
            Cat cat = InputHandler.getAnimalByInput(catService);
            System.out.println("\nСписок команд кота: ");
            System.out.println("\n" + cat.getCommands());
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
