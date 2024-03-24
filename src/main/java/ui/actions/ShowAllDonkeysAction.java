package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Donkey;
import service.DonkeyService;
import service.IService;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Component
public class ShowAllDonkeysAction implements IAction {
    @InjectByInterface(clazz = DonkeyService.class)
    private IService<Donkey> donkeyService;

    @Override
    public void execute() {
        try {
            System.out.println("\nВсе ослы по возрастанию даты рождения: ");
            List<Donkey> donkeys = donkeyService.getAllAnimals();
            for (int i = 0; i < donkeys.size(); i++) {
                System.out.println(i + 1 + ". " + donkeys.get(i));
            }
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
