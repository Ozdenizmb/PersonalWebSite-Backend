IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'util_sch')
BEGIN
EXEC('CREATE SCHEMA util_sch');
END
CREATE TABLE util_sch.asset_data
(
    id                  UNIQUEIDENTIFIER DEFAULT NEWID(),
    name                VARCHAR(255) NOT NULL UNIQUE,
    type                VARCHAR(255) NOT NULL,
    cdn_path            VARCHAR(255) NOT NULL,
    created_date        DATETIME NOT NULL,
    PRIMARY KEY (id)
);
