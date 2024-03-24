package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Базовый класс для домашних и вьючных животных.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Animal {
    private String name;
    private LocalDate birthDate;
    private List<String> commands;
}
