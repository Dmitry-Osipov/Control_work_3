package ui.actions;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import entity.Camel;
import entity.Cat;
import entity.Dog;
import entity.Donkey;
import entity.Hamster;
import entity.Horse;
import service.CamelService;
import service.CatService;
import service.DogService;
import service.DonkeyService;
import service.HamsterService;
import service.HorsesService;
import service.IService;
import utils.exceptions.ErrorMessages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Component
public class CountAllAnimalsAction implements IAction {
    @InjectByInterface(clazz = CamelService.class)
    private IService<Camel> camelService;
    @InjectByInterface(clazz = CatService.class)
    private IService<Cat> catService;
    @InjectByInterface(clazz = DogService.class)
    private IService<Dog> dogService;
    @InjectByInterface(clazz = DonkeyService.class)
    private IService<Donkey> donkeyService;
    @InjectByInterface(clazz = HamsterService.class)
    private IService<Hamster> hamsterService;
    @InjectByInterface(clazz = HorsesService.class)
    private IService<Horse> horsesService;

    @Override
    public void execute() {
        try {
            System.out.println("\nОбщее количество всех животных: ");
            System.out.println(camelService.getTotalAnimals() + catService.getTotalAnimals() +
                    dogService.getTotalAnimals() + donkeyService.getTotalAnimals() + hamsterService.getTotalAnimals() +
                    horsesService.getTotalAnimals());
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            System.out.println("\n" + ErrorMessages.FATAL_ERROR.getMessage());
        }
    }
}
