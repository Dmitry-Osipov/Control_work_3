package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Donkey;
import service.DonkeyService;
import service.IService;
import utils.InputHandler;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Component
public class ShowDonkeyCommandsAction implements IAction {
    @InjectByInterface(clazz = DonkeyService.class)
    private IService<Donkey> donkeyService;

    @Override
    public void execute() {
        try {
            Donkey donkey = InputHandler.getAnimalByInput(donkeyService);
            System.out.println("\nСписок команд осла: ");
            System.out.println("\n" + donkey.getCommands());
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
