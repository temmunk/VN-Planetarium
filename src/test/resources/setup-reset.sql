-- Use this script to set up your Planetarium database: do not edit the script

-- Enable foreign key enforcement
SET CONSTRAINTS ALL IMMEDIATE;

-- Drop tables if they exist
DROP TABLE IF EXISTS moons CASCADE;
DROP TABLE IF EXISTS planets CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create the users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL,
    CONSTRAINT username_length_check CHECK (char_length(username) >= 5 AND char_length(username) <= 30),
    CONSTRAINT password_length_check CHECK (char_length(password) >= 5 AND char_length(password) <= 30),
    CONSTRAINT username_character_check CHECK (
        username ~ '^[a-zA-Z][a-zA-Z0-9_-]*$'
    ),
    CONSTRAINT password_character_check CHECK (
        password ~ '(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])^[a-zA-Z0-9_-]+$'
    )
);

-- Insert a default user
INSERT INTO users (username, password) VALUES ('Batman', 'Iamthenight1939');

-- Create the planets table
CREATE TABLE planets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    ownerId INT NOT NULL,
    image BYTEA,
    CONSTRAINT fk_owner FOREIGN KEY (ownerId) REFERENCES users(id) ON DELETE RESTRICT,
    CONSTRAINT name_length_check CHECK (char_length(name) <= 30),
    CONSTRAINT name_character_check CHECK (
        name ~ '^[a-zA-Z][a-zA-Z0-9_ -]*$'
    )
);

-- Insert sample planets
INSERT INTO planets (name, ownerId, image) VALUES ('Earth', 1, NULL);
INSERT INTO planets (name, ownerId, image) VALUES ('Mars', 1, NULL);

-- Create the moons table
CREATE TABLE moons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    myPlanetId INT NOT NULL,
    image BYTEA,
    CONSTRAINT fk_planet FOREIGN KEY (myPlanetId) REFERENCES planets(id) ON DELETE CASCADE,
    CONSTRAINT name_length_check CHECK (char_length(name) <= 30),
    CONSTRAINT name_character_check CHECK (
        name ~ '^[a-zA-Z][a-zA-Z0-9_ -]*$'
    )
);

-- Insert sample moons
INSERT INTO moons (name, myPlanetId, image) VALUES ('Luna', 1, NULL);
INSERT INTO moons (name, myPlanetId, image) VALUES ('Titan', 2, NULL);
