# Структура проекта
database - директория с sql файлами создания и заполнения БД:
- create_database_ddl.sql - ddl-скрипт создания БД
- filling_database_dml.sql - dml-скрипт заполнения БД

linux - директория с файлом команд для линукса:
- linux_dz.txt - описание + скрипты для выполнения задания

readme - директория с файлами заданий:
- animals.png - uml-диаграмма классов
- general_control_work.docx - тз

src/main - директория с java-проектом:
- main - директория с сурсами:
  - java - sources root directory:
    - annotation - пакет аннотаций
    - dao - пакет работы с БД
    - entity - пакет сущностей
    - program - пакет запуска приложения
    - service - пакет сервисов
    - ui - пакет пользовательского интерфейса:
      - actions - пакет экшенов
    - utils - вспомогательный пакет утилитных классов:
      - exceptions - пакет исключений
      - file - пакет работы с файлами:
        - id.data - пакет файлов с id-шниками сущностей
      - validators - пакет валидаторов
  - resources - resources root directory

.gitignore - файл, указывающий гиту шаблоны для игнорирования файлов и директорий

pom.xml - project object model
