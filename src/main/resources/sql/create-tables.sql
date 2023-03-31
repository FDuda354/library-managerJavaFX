create TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    loginId   VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    surname    VARCHAR(255) NOT NULL,
    role       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    pesel      VARCHAR(255) NOT NULL,
    birthDate DATE         NOT NULL,
    joinDate  DATE         NOT NULL
);
create TABLE books
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    author           VARCHAR(255) NOT NULL,
    quantity         INTEGER      NOT NULL,
    type             VARCHAR(255) NOT NULL,
    publicationDate DATE         NOT NULL
);
create TABLE rentals
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT REFERENCES users (id),
    book_id     BIGINT REFERENCES books (id),
    rentalDate DATE    NOT NULL,
    dueDate    DATE    NOT NULL,
    quantity         INTEGER      NOT NULL,
    returnDate DATE
);