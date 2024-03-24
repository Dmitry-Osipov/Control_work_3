package program;

import annotation.factory.ApplicationContext;
import ui.MenuController;
import utils.PropertyFileConfigurator;
import utils.exceptions.ErrorMessages;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = null;
        try {
            context = new ApplicationContext(List.of("ui.actions", "dao", "service", "ui"));
            context.run(new PropertyFileConfigurator());
        } catch (IOException | InstantiationException | URISyntaxException | ClassNotFoundException |
                 InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println(ErrorMessages.PROGRAM_START_ERROR.getMessage());
        }
        MenuController controller = (MenuController) context.getComponent("menuController");
        controller.run();
    }
}
