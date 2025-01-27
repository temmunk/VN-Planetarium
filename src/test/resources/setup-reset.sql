-- PostgreSQL setup script for Planetarium database
DROP TABLE IF EXISTS moons;
DROP TABLE IF EXISTS planets;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    CONSTRAINT username_length_check CHECK (
        LENGTH(username) >= 5 AND
        LENGTH(username) <= 30
    ),
    CONSTRAINT password_length_check CHECK (
        LENGTH(password) >= 5 AND
        LENGTH(password) <= 30
    ),
    CONSTRAINT username_character_check CHECK (
        username ~ '^[a-zA-Z]' AND
        username ~ '^[a-zA-Z0-9_-]*$'
    ),
    CONSTRAINT password_character_check CHECK (
        password ~ '[a-z]' AND
        password ~ '[A-Z]' AND
        password ~ '[0-9]' AND
        password ~ '^[a-zA-Z]' AND
        password ~ '^[a-zA-Z0-9_-]*$'
    )
);

INSERT INTO users (username, password) VALUES ('Batman', 'Iamthenight1939');

CREATE TABLE planets (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    ownerId INTEGER NOT NULL,
    image BYTEA,
    FOREIGN KEY(ownerId) REFERENCES users(id) ON DELETE RESTRICT,
    CONSTRAINT name_length_check CHECK (LENGTH(name) <= 30),
    CONSTRAINT name_character_check CHECK (
        name ~ '^[a-zA-Z]' AND
        name ~ '^[a-zA-Z0-9_ -]*$'
    )
);

INSERT INTO planets (name, ownerId, image) VALUES ('Earth', 1, $1);
INSERT INTO planets (name, ownerId, image) VALUES ('Mars', 1, $1);

CREATE TABLE moons (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    myPlanetId INTEGER NOT NULL,
    image BYTEA,
    FOREIGN KEY(myPlanetId) REFERENCES planets(id) ON DELETE CASCADE,
    CONSTRAINT name_length_check CHECK (LENGTH(name) <= 30),
    CONSTRAINT name_character_check CHECK (
        name ~ '^[a-zA-Z]' AND
        name ~ '^[a-zA-Z0-9_ -]*$'
    )
);

INSERT INTO moons (name, myPlanetId, image) VALUES ('Luna', 1, $1);
INSERT INTO moons (name, myPlanetId, image) VALUES ('Titan', 2, $1);