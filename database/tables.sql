create table "user" (
    id serial not null,
    name varchar(30) not null,
    login varchar(30) unique not null primary key ,
    password varchar(50) not null
);

create table task (
    user_login varchar(30) not null references "user"(login),
    task_id serial not null,
    name varchar(100) not null,
    description varchar(1000) not null,
    creation_date date default current_date,
    deadline date check (deadline >= current_date),
    status varchar(10) default 'Planned' not null check (status in ('Planned', 'Doing', 'Done'))
);