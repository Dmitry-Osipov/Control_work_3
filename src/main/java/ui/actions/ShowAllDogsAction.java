package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Dog;
import service.DogService;
import service.IService;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Component
public class ShowAllDogsAction implements IAction {
    @InjectByInterface(clazz = DogService.class)
    private IService<Dog> dogService;

    @Override
    public void execute() {
        try {
            System.out.println("\nВсе собаки по возрастанию даты рождения: ");
            List<Dog> dogs = dogService.getAllAnimals();
            for (int i = 0; i < dogs.size(); i++) {
                System.out.println(i + 1 + ". " + dogs.get(i));
            }
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
