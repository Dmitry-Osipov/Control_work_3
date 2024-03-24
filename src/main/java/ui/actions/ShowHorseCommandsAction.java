package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Horse;
import service.HorsesService;
import service.IService;
import utils.InputHandler;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Component
public class ShowHorseCommandsAction implements IAction {
    @InjectByInterface(clazz = HorsesService.class)
    private IService<Horse> horsesService;

    @Override
    public void execute() {
        try {
            Horse horse = InputHandler.getAnimalByInput(horsesService);
            System.out.println("\nСписок команд лошади: ");
            System.out.println("\n" + horse.getCommands());
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
