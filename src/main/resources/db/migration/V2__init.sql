CREATE TABLE project_data
(
    id                  BINARY(16) DEFAULT (UNHEX(REPLACE(UUID(), '-', ''))) PRIMARY KEY,
    name                VARCHAR(255) NOT NULL UNIQUE,
    description         VARCHAR(255) NOT NULL,
    url                 VARCHAR(255) NOT NULL,
    technologies        VARCHAR(255) NOT NULL,
    image_url           VARCHAR(255) NOT NULL,
    created_date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);