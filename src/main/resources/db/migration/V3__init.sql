IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'util_sch')
BEGIN
EXEC('CREATE SCHEMA util_sch');
END
CREATE TABLE util_sch.comment_data
(
    id                  UNIQUEIDENTIFIER DEFAULT NEWID(),
    user_id             UNIQUEIDENTIFIER NOT NULL,
    project_id          UNIQUEIDENTIFIER NOT NULL,
    text                VARCHAR(255) NOT NULL,
    created_date        DATETIME NOT NULL,
    updated_date        DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id, project_id),
    FOREIGN KEY (user_id) REFERENCES util_sch.user_data(id),
    FOREIGN KEY (project_id) REFERENCES util_sch.project_data(id)
);
