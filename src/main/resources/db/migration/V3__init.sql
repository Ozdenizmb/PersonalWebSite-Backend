CREATE TABLE comment_data
(
    id                  BINARY(16) DEFAULT (UNHEX(REPLACE(UUID(), '-', ''))) PRIMARY KEY,
    user_id             BINARY(16) NOT NULL,
    project_id          BINARY(16) NOT NULL,
    text                VARCHAR(255) NOT NULL,
    created_date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (user_id, project_id),
    FOREIGN KEY (user_id) REFERENCES user_data(id),
    FOREIGN KEY (project_id) REFERENCES project_data(id)
);