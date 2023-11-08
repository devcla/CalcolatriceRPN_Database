CREATE DATABASE IF NOT EXISTS calcolatrice_rpn;
USE calcolatrice_rpn;

CREATE TABLE IF NOT EXISTS users(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS operations(
    id INT AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    operation VARCHAR(300) NOT NULL,
    result VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (username) REFERENCES users(username)
);