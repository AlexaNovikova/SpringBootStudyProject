BEGIN;

DROP TABLE IF EXISTS categories CASCADE;
CREATE TABLE categories (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO categories (name) VALUES
('Category 1'),
('Category 2'),
('Category 3');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, name VARCHAR(255), price int, category_id bigint REFERENCES categories(id));
INSERT INTO products (name, price, category_id) VALUES
('Product 1', 1100, 2),
('Product 2', 1120, 1),
('Product 3', 6100, 3),
('Product 4', 1250, 1),
('Product 5', 2750, 1),
('Product 6', 7500, 2);



COMMIT;