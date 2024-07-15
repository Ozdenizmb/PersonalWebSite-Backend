CREATE SCHEMA IF NOT EXISTS util_sch;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS util_sch.project_data
(
    id                  uuid DEFAULT uuid_generate_v4(),
    name                        VARCHAR NOT NULL UNIQUE,
    description                 VARCHAR NOT NULL,
    url                         VARCHAR NOT NULL,
    technologies                VARCHAR NOT NULL,
    image_url                   VARCHAR NOT NULL,
    created_date                DATE NOT NULL,
    updated_date                DATE NOT NULL,
    PRIMARY KEY (id)
);
