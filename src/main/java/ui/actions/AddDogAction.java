package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Dog;
import service.DogService;
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
public class AddDogAction implements IAction {
    @InjectByInterface(clazz = DogService.class)
    private IService<Dog> dogService;

    @Override
    public void execute() {
        try {
            int id = AnimalOperationHelper.getId(dogService);

            System.out.println("\nВведите кличку собаки: ");
            String name = AnimalOperationHelper.getName();

            System.out.println("\nВведите дату рождения собаки в формате ГГГГ-ММ-ДД: ");
            LocalDate birthDate = AnimalOperationHelper.getBirthDate();

            System.out.println("\nВведите список команд собаки через запятую и пробел (, ): ");
            List<String> list = AnimalOperationHelper.getCommands();

            dogService.add(new Dog(id, name, birthDate, list));
            System.out.println("\nУдалось добавить новую собаку");
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
