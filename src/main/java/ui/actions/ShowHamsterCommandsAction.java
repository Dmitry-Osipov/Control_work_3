package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Hamster;
import service.HamsterService;
import service.IService;
import utils.InputHandler;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Component
public class ShowHamsterCommandsAction implements IAction {
    @InjectByInterface(clazz = HamsterService.class)
    private IService<Hamster> hamsterService;

    @Override
    public void execute() {
        try {
            Hamster hamster = InputHandler.getAnimalByInput(hamsterService);
            System.out.println("\nСписок команд хомяка: ");
            System.out.println("\n" + hamster.getCommands());
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
