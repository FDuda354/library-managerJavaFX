--users <-> rentals: Jeden użytkownik może mieć wiele wypożyczeń, ale każde wypożyczenie jest przypisane tylko do jednego użytkownika. W tabeli rentals, kolumna user_id jest kluczem obcym, który odnosi się do kolumny id w tabeli users.
--books <-> rentals: Jedna książka może być wypożyczona wiele razy, ale każde wypożyczenie jest przypisane tylko do jednej książki. W tabeli rentals, kolumna book_id jest kluczem obcym, który odnosi się do kolumny id w tabeli books.
--users <-> overdue_fees: Jeden użytkownik może mieć wiele opłat za przeterminowane książki, ale każda opłata za przeterminowanie jest przypisana tylko do jednego użytkownika. W tabeli overdue_fees, kolumna user_id jest kluczem obcym, który odnosi się do kolumny id w tabeli users.
--books <-> overdue_fees: Jedna książka może mieć wiele opłat za przeterminowanie, ale każda opłata za przeterminowanie jest przypisana tylko do jednej książki. W tabeli overdue_fees, kolumna book_id jest kluczem obcym, który odnosi się do kolumny id w tabeli books.
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
create TABLE overdue_fees
(
    id           BIGSERIAL PRIMARY KEY,
    daysOverdue INTEGER   NOT NULL,
    fee          numeric(15,2)   NOT NULL,
    rental_id      BIGINT REFERENCES rentals (id),
    user_id      BIGINT REFERENCES users (id),
    dueDate     TIMESTAMP NOT NULL,
    returnDate  TIMESTAMP NOT NULL
);


