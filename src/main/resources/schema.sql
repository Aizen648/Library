CREATE TABLE BOOK (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NULL,
    title VARCHAR(2000) NULL,
    author VARCHAR(2000) NULL,
    created timestamp
);
CREATE TABLE CLIENT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(400) NOT NULL,
    last_name VARCHAR(2000) NULL,
    created timestamp
);

ALTER TABLE BOOK
    ADD CONSTRAINT book_client_id
    FOREIGN KEY (client_id) REFERENCES client(id)