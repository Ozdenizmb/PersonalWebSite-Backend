CREATE SCHEMA IF NOT EXISTS util_sch;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS util_sch.like_data
(
    id                  uuid DEFAULT uuid_generate_v4(),
    user_id                     uuid NOT NULL,
    project_id                  uuid NOT NULL,
    created_date                DATE NOT NULL,
    updated_date                DATE NOT NULL,
    UNIQUE (user_id, project_id),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES util_sch.user_data(id),
    FOREIGN KEY (project_id) REFERENCES util_sch.project_data(id)
);
