CREATE TABLE category
(
id IDENTITY,
name VARCHAR(50),
description VARCHAR(255),
image_url VARCHAR(50),
is_active BOOLEAN,

CONSTRAINT pk_category_id PRIMARY KEY(id)

);

INSERT INTO category (name, description,image_url,is_active) VALUES ('Laptop','This is some Description For Laptop','CAT_1.png',true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Television','This is some Description For Television','CAT_3.png',true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Mobile','This is some Description For Mobile','CAT_2.png',true);

CREATE TABLE user_detail
(
id IDENTITY,
first_name VARCHAR(50),
last_name VARCHAR(50),
role VARCHAR(50),
enabled BOOLEAN,
password VARCHAR(50),
email VARCHAR(100),
contact_number VARCHAR(15),
CONSTRAINT pk_user_id PRIMARY KEY (id),
);

INSERT INTO user_detail
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Akash', 'Kumar', 'Admin', true, 'admin', 'Ak22610@gmail.com', '9999-9999');
INSERT INTO user_detail
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Rabindra', 'Sharma', 'SUPPLIER', true, '12345', 'RS@gmail.com', '8888-9999');
INSERT INTO user_detail
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Simran', 'Chawla', 'SUPPLIER', true, 'admin', 'SC@gmail.com', '9999-8888');

CREATE TABLE product
(
id IDENTITY,
code VARCHAR(20),
name VARCHAR(50),
brand VARCHAR(50),
description VARCHAR(255),
unit_price DECIMAL(10,2),
quantity INT,
is_active BOOLEAN,
category_id INT,
supplier_id INT,
purchases INT DEFAULT 0,
views INT DEFAULT 0,
CONSTRAINT pk_product_id PRIMARY KEY (id),
CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category(id),
CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id),
);

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES('PRDABC123DEFX', 'Iphone6s', 'apple', 'This is one of the best phone available in the market', 18000, 5, true, 3, 2);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES('PRDDEF456FGHZ', 'S7', 'Samsung', 'This is one of the best smart phone made my Samsung', 32000, 2, true, 3, 3);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES('PRDGHF678HIJY', 'Pixel2', 'Google', 'This is one of the best Smart plus quality phone ever', 60000, 5, true, 3, 2);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES('PRDRTY891SYDF', 'MACBOOK Pro', 'apple', 'This is one of the Smartest laptop ever to use', 54000, 3, true, 1, 2);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES('PRDHKJ765DOPL', 'Dell Latitude E6510', 'Dell', 'This is one of the best laptop ever design', 48000, 5, true, 1, 3);



/* 
 * to drop table we can use this statemnt 
 * "DROP TABLE *TABLE NAME*"
 * */







