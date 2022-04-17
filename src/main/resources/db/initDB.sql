drop table IF EXISTS users;
drop table IF EXISTS roles;
drop table IF EXISTS user_roles;

create TABLE users (
    id          SERIAL PRIMARY KEY,
    username    VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

create TABLE roles (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL
);

create TABLE user_roles (
    user_id     INTEGER NOT NULL,
    role_id     INTEGER NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

create UNIQUE INDEX users_role_unique_idx ON user_roles (user_id, role_id);

insert into users (username, password) values ('Admin', '111111111111');
insert into roles (name) values ('ROLE_ADMIN'), ('ROLE_USER');
insert into user_roles (user_id, role_id) values(1, 1);