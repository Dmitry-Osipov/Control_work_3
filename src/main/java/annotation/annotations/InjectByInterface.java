package annotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Указывает, что поле должно быть автоматически связано с зависимостью с помощью внедрения зависимостей к указанному
 * классу. Следует применять к полям, которые являются интерфейсами.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectByInterface {
    Class<?> clazz();
}
