DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name TEXT,
    age INT,
    PRIMARY KEY (id)
);

CREATE TABLE books (
    isbn VARCHAR(255) NOT NULL,
    title TEXT,
    author_id BIGINT,
    PRIMARY KEY (isbn),
    CONSTRAINT fk_author FOREIGN KEY (author_id)
        REFERENCES authors(id)
);
