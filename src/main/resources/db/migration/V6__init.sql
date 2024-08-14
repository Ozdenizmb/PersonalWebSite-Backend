CREATE TABLE contact_data
(
    id                  BINARY(16) DEFAULT (UNHEX(REPLACE(UUID(), '-', ''))) PRIMARY KEY,
    name                VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL,
    subject             VARCHAR(255) NOT NULL,
    message             VARCHAR(1000) NOT NULL,  -- Mesaj için daha uzun bir alan
    created_date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);