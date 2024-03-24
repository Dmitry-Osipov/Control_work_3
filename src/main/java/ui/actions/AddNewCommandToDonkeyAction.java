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
public class AddNewCommandToDonkeyAction implements IAction {
    @InjectByInterface(clazz = DonkeyService.class)
    private IService<Donkey> donkeyService;

    @Override
    public void execute() {
        try {
            Donkey donkey = InputHandler.getAnimalByInput(donkeyService);
            System.out.println("\nВведите команду, которой надо обучить осла: ");
            String command = InputHandler.getUserInput();
            donkeyService.addNewCommand(donkey, command);
            System.out.println("\nУдалось обновить список команд осла");
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
