CREATE TABLE asset_data
(
    id                  BINARY(16) DEFAULT (UNHEX(REPLACE(UUID(), '-', ''))) PRIMARY KEY,
    name                VARCHAR(255) NOT NULL UNIQUE,
    type                VARCHAR(255) NOT NULL,
    cdn_path            VARCHAR(255) NOT NULL,
    created_date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);