-- Удалить записи о верблюдах и объединить таблицы лошадей и ослов.
DELETE FROM human_friends.pack_animal WHERE type = 'Camel';

CREATE TABLE human_friends.horse_and_donkey (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(256) NOT NULL,
    type VARCHAR(32) NOT NULL,
    birth_date DATE NOT NULL,
    commands VARCHAR(256) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO human_friends.horse_and_donkey (name, type, birth_date, commands)
SELECT name, type, birth_date, commands FROM human_friends.pack_animal WHERE type = 'Horse';

INSERT INTO human_friends.horse_and_donkey (name, type, birth_date, commands)
SELECT name, type, birth_date, commands FROM human_friends.pack_animal WHERE type = 'Donkey';

-- Создать новую таблицу для животных в возрасте от 1 до 3 лет и вычислить их возраст с точностью до месяца.
CREATE TABLE human_friends.animal_age AS
SELECT *,
       DATEDIFF(CURRENT_DATE, birth_date) DIV 30 AS age_months
FROM human_friends.pet
WHERE DATEDIFF(CURRENT_DATE, birth_date) BETWEEN 30 AND 1095;

INSERT INTO human_friends.animal_age (name, type, birth_date, commands, age_months)
SELECT name, type, birth_date, commands, DATEDIFF(CURRENT_DATE, birth_date) DIV 30 AS age_months
FROM human_friends.pack_animal
WHERE DATEDIFF(CURRENT_DATE, birth_date) BETWEEN 30 AND 1095;

-- Объединить все созданные таблицы в одну, сохраняя информацию о принадлежности к исходным таблицам.
CREATE TABLE human_friends.all_animals AS (
    SELECT id, name, 'pet' AS source_table, type, birth_date, commands
    FROM human_friends.pet
    UNION ALL
    SELECT id, name, 'pack_animal' AS source_table, type, birth_date, commands
    FROM human_friends.pack_animal
);
