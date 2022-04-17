DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS notebooks;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id          SERIAL PRIMARY KEY,
    username    VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

CREATE TABLE roles (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL
);

CREATE TABLE user_roles (
    user_id     INTEGER NOT NULL,
    role_id     INTEGER NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

create UNIQUE INDEX users_role_unique_idx ON user_roles (user_id, role_id);

CREATE TABLE notebooks (
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER NOT NULL,
    name        VARCHAR NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE

)

CREATE TABLE notes(
    id              SERIAL PRIMARY KEY,
    notebook_id     INTEGER NOT NULL,
    user_id         INTEGER NOT NULL,
    name            VARCHAR NOT NULL,
    content         VARCHAR NOT NULL,
    last_modified   TIMESTAMP NOT NULL,

    FOREIGN KEY (notebook_id) REFERENCES notebooks (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE

)

INSERT INTO users (username, password)
    VALUES
    ('Admin', '$2a$11$nB7Qkyl33Klskeyx8o2tquHuQDT9nJVcynW50XYb66kEhmAAyUcGG'),
    ('DemoUser', '$2a$11$nB7Qkyl33Klskeyx8o2tquHuQDT9nJVcynW50XYb66kEhmAAyUcGG');

INSERT INTO roles (name)
    VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

INSERT INTO user_roles (user_id, role_id)
    VALUES
    (1, 1),
    (1, 2),
    (2, 2);