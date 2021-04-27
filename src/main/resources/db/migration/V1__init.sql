BEGIN;

DROP TABLE IF EXISTS categories CASCADE;
CREATE TABLE categories (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO categories (name) VALUES
('Food'),
('Technic'),
('Clothes');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, name VARCHAR(255), price int, category_id bigint REFERENCES categories(id));
INSERT INTO products (name, price, category_id) VALUES
('Trousers', 2000, 3),
('Dress', 1120, 3),
('Skirt', 4100, 3),
('Jeans', 4250, 3),
('Computer', 42750, 2),
('Printer', 26000, 2),
('Bread', 40, 1),
('Milk', 60, 1),
('Yogurt', 54, 1),
('Cheese', 670, 1),
('Scanner', 17500, 2);



COMMIT;