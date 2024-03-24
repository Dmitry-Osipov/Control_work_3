package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Camel;
import service.CamelService;
import service.IService;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Component
public class ShowAllCamelsAction implements IAction {
    @InjectByInterface(clazz = CamelService.class)
    private IService<Camel> camelService;

    @Override
    public void execute() {
        try {
            System.out.println("\nВсе верблюды по возрастанию даты рождения: ");
            List<Camel> camels = camelService.getAllAnimals();
            for (int i = 0; i < camels.size(); i++) {
                System.out.println(i + 1 + ". " + camels.get(i));
            }
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
