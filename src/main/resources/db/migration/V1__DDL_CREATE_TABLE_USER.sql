CREATE TABLE IF NOT EXISTS users
(
    id          VARCHAR(36)         NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
    username    VARCHAR(255) UNIQUE NOT NULL,
    password    VARCHAR(255)        NOT NULL,
    email       VARCHAR(255) UNIQUE NOT NULL,
    img_link    VARCHAR(2000)       NOT NULL,
    user_status INT                 NOT NULL DEFAULT 0 CHECK ( user_status >= 0 AND user_status <= 1),
    created_at  TIMESTAMP           NOT NULL,
    updated_at  TIMESTAMP           NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);


