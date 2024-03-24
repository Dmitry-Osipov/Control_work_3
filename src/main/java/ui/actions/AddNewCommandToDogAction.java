package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Dog;
import service.DogService;
import service.IService;
import utils.InputHandler;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Component
public class AddNewCommandToDogAction implements IAction {
    @InjectByInterface(clazz = DogService.class)
    private IService<Dog> dogService;


    @Override
    public void execute() {
        try {
            Dog dog = InputHandler.getAnimalByInput(dogService);
            System.out.println("\nВведите команду, которой надо обучить собаку: ");
            String command = InputHandler.getUserInput();
            dogService.addNewCommand(dog, command);
            System.out.println("\nУдалось обновить список команд собаки");
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
