package utils.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс предназначен для валидации даты.
 */
public final class StringToLocalDateValidator {
    public static final String LOCAL_DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}$";

    private StringToLocalDateValidator() {
    }

    /**
     * Метод проверяет номер телефона.
     * @param date Номер телефона.
     * @return {@code true}, если формат номера телефона корректен, иначе {@code false}.
     */
    public static boolean validateLocalDate(String date) {
        Pattern pattern = Pattern.compile(LOCAL_DATE_REGEX);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}
