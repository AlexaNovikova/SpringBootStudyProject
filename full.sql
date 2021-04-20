BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, name VARCHAR(255), price int);
INSERT INTO products (name, price) VALUES
('Product 1', 1100),
('Product 2', 1120),
('Product 3', 6100),
('Product 4', 1250),
('Product 5', 2750),
('Product 6', 7500);

COMMIT;