DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS notebooks;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id                  SERIAL PRIMARY KEY,
                       username            VARCHAR NOT NULL,
                       password            VARCHAR NOT NULL,
                       registration_date   TIMESTAMP NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE notebooks (
                           id          SERIAL PRIMARY KEY,
                           user_id     INTEGER NOT NULL,
                           name        VARCHAR NOT NULL,

                           FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE

);

CREATE TABLE notes(
                      id              SERIAL PRIMARY KEY,
                      notebook_id     INTEGER NOT NULL,
                      user_id         INTEGER NOT NULL,
                      name            VARCHAR NOT NULL,
                      content         VARCHAR NOT NULL,
                      last_modified   TIMESTAMP NOT NULL,

                      FOREIGN KEY (notebook_id) REFERENCES notebooks (id) ON DELETE CASCADE,
                      FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE

);

INSERT INTO users (username, password, registration_date)
VALUES
('Admin', '$2a$11$nB7Qkyl33Klskeyx8o2tquHuQDT9nJVcynW50XYb66kEhmAAyUcGG', '2022-04-23 21:35:37.585000'),
('DemoUser', '$2a$11$nB7Qkyl33Klskeyx8o2tquHuQDT9nJVcynW50XYb66kEhmAAyUcGG', '2022-04-23 21:35:37.585000');

INSERT INTO user_roles (user_id, role)
VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(2, 'ROLE_USER');