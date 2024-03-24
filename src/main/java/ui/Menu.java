package ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс меню.
 */
@Getter
@AllArgsConstructor
@ToString
public class Menu {
    private final String name;
    private final MenuItem[] menuItems;
}
