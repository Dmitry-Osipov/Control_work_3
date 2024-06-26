package ui.actions;

/**
 * Интерфейс IAction предназначен для выполнения действий. Классы, реализующие этот интерфейс, могут использоваться для
 * представления логики, которая будет выполнена при выборе соответствующего пункта меню.
 */
public interface IAction {
    /**
     * Метод выполняет определенное действие в соответствии с реализацией интерфейса.
     */
    void execute();
}
