IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'util_sch')
BEGIN
EXEC('CREATE SCHEMA util_sch');
END
CREATE TABLE util_sch.contact_data
(
    id                  UNIQUEIDENTIFIER DEFAULT NEWID(),
    name                VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL,
    subject             VARCHAR(255) NOT NULL,
    message             VARCHAR(1000) NOT NULL,  -- Mesaj için daha uzun bir alan
    created_date        DATETIME NOT NULL,
    updated_date        DATETIME NOT NULL,
    PRIMARY KEY (id)
);
