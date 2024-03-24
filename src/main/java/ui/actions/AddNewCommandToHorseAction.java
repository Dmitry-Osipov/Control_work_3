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
public class AddNewCommandToHorseAction implements IAction {
    @InjectByInterface(clazz = HorsesService.class)
    private IService<Horse> horsesService;

    @Override
    public void execute() {
        try {
            Horse horse = InputHandler.getAnimalByInput(horsesService);
            System.out.println("\nВведите команду, которой надо обучить лошадь: ");
            String command = InputHandler.getUserInput();
            horsesService.addNewCommand(horse, command);
            System.out.println("\nУдалось обновить список команд лошади");
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
