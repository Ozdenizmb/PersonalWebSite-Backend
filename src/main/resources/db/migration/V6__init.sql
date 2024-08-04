CREATE SCHEMA IF NOT EXISTS util_sch;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS util_sch.contact_data
(
    id                  uuid DEFAULT uuid_generate_v4(),
    name                      VARCHAR NOT NULL,
    email                     VARCHAR NOT NULL,
    subject                   VARCHAR NOT NULL,
    message                   VARCHAR NOT NULL,
    created_date              DATE NOT NULL,
    updated_date              DATE NOT NULL,
    PRIMARY KEY (id)
);
