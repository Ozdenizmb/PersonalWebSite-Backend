IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'util_sch')
BEGIN
EXEC('CREATE SCHEMA util_sch');
END
CREATE TABLE util_sch.project_data
(
    id                  UNIQUEIDENTIFIER DEFAULT NEWID(),
    name                VARCHAR(255) NOT NULL UNIQUE,
    description         VARCHAR(255) NOT NULL,
    url                 VARCHAR(255) NOT NULL,
    technologies        VARCHAR(255) NOT NULL,
    image_url           VARCHAR(255) NOT NULL,
    created_date        DATETIME NOT NULL,
    updated_date        DATETIME NOT NULL,
    PRIMARY KEY (id)
);
