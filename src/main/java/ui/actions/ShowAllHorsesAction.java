package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Horse;
import service.HorsesService;
import service.IService;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Component
public class ShowAllHorsesAction implements IAction {
    @InjectByInterface(clazz = HorsesService.class)
    private IService<Horse> horsesService;

    @Override
    public void execute() {
        try {
            System.out.println("\nВсе хомяки по возрастанию даты рождения: ");
            List<Horse> horses = horsesService.getAllAnimals();
            for (int i = 0; i < horses.size(); i++) {
                System.out.println(i + 1 + ". " + horses.get(i));
            }
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
