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
 * Класс верблюда.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Camel extends PackAnimal implements Identifiable {
    private int id;
    private String name;
    private LocalDate birthDate;
    private List<String> commands;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camel camel = (Camel) o;
        return getId() == camel.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
