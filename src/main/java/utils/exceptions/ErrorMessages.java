package utils.exceptions;

import lombok.Getter;

/**
 * Класс предназначен для отработки сообщений об ошибке.
 */
@Getter
public enum ErrorMessages {
    INCORRECT_INPUT("Неверный ввод. Повторите: "),
    NOT_INSERT_DATA_TO_DB("Не удалось вставить данные в БД"),
    NOT_UPDATE_DATA_TO_DB("Не удалось обновить данные в БД"),
    NO_ENTITY_EXCEPTION("Отсутствуют сущности"),
    FILE_ERROR("Произошла ошибка обработки файла"),
    FATAL_ERROR("Произошла непредвиденная ошибка"),
    PROGRAM_START_ERROR("Невозможно запустить программу");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
