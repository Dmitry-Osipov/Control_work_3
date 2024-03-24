package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Cat;
import service.CatService;
import service.IService;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Component
public class ShowAllCatsAction implements IAction {
    @InjectByInterface(clazz = CatService.class)
    private IService<Cat> catService;

    @Override
    public void execute() {
        try {
            System.out.println("\nВсе коты по возрастанию даты рождения: ");
            List<Cat> cats = catService.getAllAnimals();
            for (int i = 0; i < cats.size(); i++) {
                System.out.println(i + 1 + ". " + cats.get(i));
            }
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
