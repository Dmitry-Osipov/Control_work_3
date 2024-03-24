package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Camel;
import service.CamelService;
import service.IService;
import utils.InputHandler;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Component
public class ShowCamelCommandsAction implements IAction{
    @InjectByInterface(clazz = CamelService.class)
    private IService<Camel> camelService;

    @Override
    public void execute() {
        try {
            Camel camel = InputHandler.getAnimalByInput(camelService);
            System.out.println("\nСписок команд верблюда: ");
            System.out.println("\n" + camel.getCommands());
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
