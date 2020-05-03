drop table if exists users cascade;
create table users(
      id serial primary key,
      email varchar not null unique,
      password varchar not null
);

drop table if exists categories cascade;
create table categories(
      id serial primary key,
      user_id integer references users(id) not null,
      name varchar not null,
      description varchar,
      budget numeric check (budget >= 0),
      unique(user_id, name)
);

drop type if exists record_type;
create type record_type as enum ('credit', 'debit');

drop table if exists records;
create table records(
      id serial primary key,
      user_id integer references users(id) not null,
      category_id integer references categories(id) not null,
      type record_type not null,
      description varchar,
      amount numeric not null
);
