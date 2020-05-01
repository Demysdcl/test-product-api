CREATE TABLE product (
    product_id BIGINT NOT NULL PRIMARY KEY,
    description VARCHAR NOT NULL,
    unitPrice DOUBLE NOT NULL,
    creationDate DATE NOT NULL,
    enable BIT
);