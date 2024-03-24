package utils;

import entity.Animal;

import java.time.chrono.ChronoLocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * Компаратор, сравнивающий даты рождения животных.
 */
public class AnimalBirthDateComparator implements Comparator<Animal> {
    /**
     * Метод сравнения животных по дате рождения.
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return итог сравнения.
     */
    @Override
    public int compare(Animal o1, Animal o2) {
        return Objects.compare(o1.getBirthDate(), o2.getBirthDate(), Comparator.nullsLast(ChronoLocalDate::compareTo));
    }
}
