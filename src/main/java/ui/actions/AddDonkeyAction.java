package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Donkey;
import service.DonkeyService;
import service.IService;
import utils.AnimalOperationHelper;
import utils.exceptions.ErrorMessages;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Component
public class AddDonkeyAction implements IAction {
    @InjectByInterface(clazz = DonkeyService.class)
    private IService<Donkey> donkeyService;

    @Override
    public void execute() {
        try {
            int id = AnimalOperationHelper.getId(donkeyService);

            System.out.println("\nВведите кличку осла: ");
            String name = AnimalOperationHelper.getName();

            System.out.println("\nВведите дату рождения осла в формате ГГГГ-ММ-ДД: ");
            LocalDate birthDate = AnimalOperationHelper.getBirthDate();

            System.out.println("\nВведите список команд осла через запятую и пробел (, ): ");
            List<String> list = AnimalOperationHelper.getCommands();

            donkeyService.add(new Donkey(id, name, birthDate, list));
            System.out.println("\nУдалось добавить нового осла");
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        } catch (IOException e) {
            System.out.println("\n" + ErrorMessages.FILE_ERROR.getMessage());
        } catch (DateTimeException e) {
            System.out.println("\n" + ErrorMessages.NOT_INSERT_DATA_TO_DB.getMessage() + ". Некорректные данные даты");
        }
    }
}
