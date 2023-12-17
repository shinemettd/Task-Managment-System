CREATE TABLE "user" (
    id SERIAL NOT NULL,
    name VARCHAR(30) NOT NULL,
    login VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE task (
    user_login VARCHAR(30) NOT NULL REFERENCES "user"(login),
    task_id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    creation_date DATE DEFAULT current_date,
    status VARCHAR(10) DEFAULT 'Planned' NOT NULL CHECK (status IN ('Planned', 'Doing', 'Done'))
);