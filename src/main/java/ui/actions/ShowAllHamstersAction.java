package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Hamster;
import service.HamsterService;
import service.IService;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Component
public class ShowAllHamstersAction implements IAction {
    @InjectByInterface(clazz = HamsterService.class)
    private IService<Hamster> hamsterService;

    @Override
    public void execute() {
        try {
            System.out.println("\nВсе хомяки по возрастанию даты рождения: ");
            List<Hamster> hamsters = hamsterService.getAllAnimals();
            for (int i = 0; i < hamsters.size(); i++) {
                System.out.println(i + 1 + ". " + hamsters.get(i));
            }
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
