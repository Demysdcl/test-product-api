CREATE TABLE product (
    product_id BIGINT NOT NULL PRIMARY KEY,
    description VARCHAR NOT NULL,
    unit_price DOUBLE NOT NULL,
    creation_date DATE NOT NULL,
    enable BIT
);