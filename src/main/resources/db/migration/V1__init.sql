IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'util_sch')
BEGIN
EXEC('CREATE SCHEMA util_sch');
END
CREATE TABLE util_sch.user_data
(
    id UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) UNIQUE,
    biography VARCHAR(255),
    phone_number VARCHAR(50),
    profession VARCHAR(100),
    birthday DATE,
    role VARCHAR(50) NOT NULL,
    created_date DATETIME NOT NULL DEFAULT GETDATE(),
    updated_date DATETIME NOT NULL DEFAULT GETDATE()
);
