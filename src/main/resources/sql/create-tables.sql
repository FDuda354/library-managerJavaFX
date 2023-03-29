CREATE TABLE books
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    author           VARCHAR(255) NOT NULL,
    quantity         INTEGER      NOT NULL,
    type             VARCHAR(255) NOT NULL,
    publicationDate DATE         NOT NULL
);
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    login_id   VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    surname    VARCHAR(255) NOT NULL,
    role       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    pesel      VARCHAR(255) NOT NULL,
    birthDate DATE         NOT NULL,
    joinDate  DATE         NOT NULL
);
CREATE TABLE overdue_fees
(
    id           BIGSERIAL PRIMARY KEY,
    days_overdue INTEGER   NOT NULL,
    fee          INTEGER   NOT NULL,
    book_id      BIGINT REFERENCES books (id),
    user_id      BIGINT REFERENCES users (id),
    dueDate     TIMESTAMP NOT NULL,
    returnDate  TIMESTAMP NOT NULL
);

