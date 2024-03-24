package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Класс осла.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Donkey extends PackAnimal implements Identifiable {
    private int id;
    private String name;
    private LocalDate birthDate;
    private List<String> commands;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donkey donkey = (Donkey) o;
        return getId() == donkey.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
