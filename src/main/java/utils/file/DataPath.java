package utils.file;

import lombok.Getter;

/**
 * Перечисление DataPath представляет собой константы директорий и файлов.
 * Каждый элемент перечисления содержит соответствующий путь из property файла.
 */
@Getter
public enum DataPath {
    PROPERTY_FILE("src/main/resources/config.properties"),
    ID_DIRECTORY(PropertyFileReader.getValue("id_directory"));

    private final String path;

    DataPath(String path) {
        this.path = path;
    }
}
