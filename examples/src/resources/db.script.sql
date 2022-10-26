create table if not exists users
(
    id       integer,
    login    varchar(128),
    password varchar(128)
);

alter table users
    owner to makhym;

