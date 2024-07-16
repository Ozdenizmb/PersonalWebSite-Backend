CREATE SCHEMA IF NOT EXISTS util_sch;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS util_sch.user_data
(
    id                  uuid DEFAULT uuid_generate_v4(),
    first_name                  VARCHAR NOT NULL,
    last_name                   VARCHAR NOT NULL,
    email                       VARCHAR NOT NULL UNIQUE,
    password                    VARCHAR NOT NULL,
    image_url                   VARCHAR UNIQUE,
    biography                   VARCHAR,
    profession                  VARCHAR,
    birthday                    DATE,
    role                        VARCHAR NOT NULL,
    created_date                DATE NOT NULL,
    updated_date                DATE NOT NULL,
    PRIMARY KEY (id)
);
