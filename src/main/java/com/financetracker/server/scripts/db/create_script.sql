drop table if exists users cascade;
drop table if exists categories cascade;
drop table if exists records;

create table users(id serial primary key, username varchar, password varchar);

create table categories(id serial primary key, user_id integer references users(id), name varchar not null, description varchar, budget numeric);

create type record_type as enum ('credit', 'debit');

create table records(id serial primary key, user_id integer references users(id) not null, category_id integer
                    references categories(id) not null, type record_type not null, description varchar, amount numeric not null);
