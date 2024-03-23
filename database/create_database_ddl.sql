CREATE SCHEMA `human_friends` DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE human_friends.pet (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(256) NOT NULL,
    type VARCHAR(32) NOT NULL,
    birth_date DATE NOT NULL,
    commands VARCHAR(256) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE human_friends.pack_animal (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(256) NOT NULL,
    type VARCHAR(32) NOT NULL,
    birth_date DATE NOT NULL,
    commands VARCHAR(256) NOT NULL,
    PRIMARY KEY (id)
);
