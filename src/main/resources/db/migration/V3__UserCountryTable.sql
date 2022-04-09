CREATE TABLE person2 (
                        id UUID PRIMARY KEY NOT NULL,
                        name varchar(255) NOT NULL
);

create table users(
                      id SERIAL PRIMARY KEY,
                      name varchar(100) NOT NULL,
                      status INT
);

CREATE TABLE country (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(128) NOT NULL

);

create table book(
                     id SERIAL PRIMARY KEY,
                     name varchar(128) NOT NULL
);
