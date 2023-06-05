USE `customer-service`;

INSERT INTO tbl_regions (id, name) VALUES (1, 'Sudamerica');
INSERT INTO tbl_regions (id, name) VALUES (2, 'Centroamerica');
INSERT INTO tbl_regions (id, name) VALUES (3, 'Norteamerica');
INSERT INTO tbl_regions (id, name) VALUES (4, 'Europa');
INSERT INTO tbl_regions (id, name) VALUES (5, 'Asia');
INSERT INTO tbl_regions (id, name) VALUES (6, 'Africa');
INSERT INTO tbl_regions (id, name) VALUES (7, 'Oceania');
INSERT INTO tbl_regions (id, name) VALUES (8, 'Antártida');

INSERT INTO tbl_customers (id,number_id,first_name,last_name, email, photo_url, region_id, state) VALUES(1,'32404580', 'Romina', 'Guzman', 'rguzman@mail.com','',1,'CREATED');
INSERT INTO tbl_customers (id,number_id,first_name,last_name, email, photo_url, region_id, state) VALUES(2,'31489288', 'Ramiro', 'Lopez', 'rlopez@mail.com','',4,'CREATED');