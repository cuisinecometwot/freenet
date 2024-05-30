SET client_encoding = 'UTF8';
CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE EXTENSION IF NOT EXISTS unaccent;
--drop database if exists cybercafe;
--create database cybercafe;
--\c cybercafe

CREATE TABLE "User" (
  "username" varchar(30) UNIQUE PRIMARY KEY,
  "name" varchar(30) NOT NULL,
  "email" varchar(30),
  "phone_number" char(11),
  "balance" int DEFAULT 0,
  "password" varchar(30) NOT NULL,
  "role" varchar(10),
  "wage" int DEFAULT 0
);

CREATE TABLE "Sessions" (
  "session_id" BIGSERIAL PRIMARY KEY NOT NULL,
  "username" varchar(30) NOT NULL,
  "start_time" timestamp NOT NULL,
  "end_time" timestamp NOT NULL,
  "total_cost" int NOT NULL,
  "host_id" int NOT NULL
);

CREATE TABLE "Computers" (
  "host_id" int PRIMARY KEY NOT NULL,
  "username" varchar(30) UNIQUE DEFAULT null,
  "ip_address" char(15),
  "configuration" varchar(200),
  "cost_perhour" int DEFAULT 5000,
  "status" char(10) NOT NULL
);

CREATE TABLE "Orders" (
  "order_id" int PRIMARY KEY NOT NULL,
  "username" varchar(30) NOT NULL,
  "order_time" timestamp NOT NULL,
  "status" varchar(10) NOT NULL,
  "total_cost" int NOT NULL
);

CREATE TABLE "OrderItems" (
  "item_id" BIGSERIAL PRIMARY KEY,
  "order_id" int NOT NULL,
  "quantity" int NOT NULL,
  "sub_total" int NOT NULL,
  "fdservice_id" int NOT NULL
);

CREATE TABLE "FDService" (
  "fdservice_id" BIGSERIAL PRIMARY KEY,
  "name" varchar(20) NOT NULL,
  "cost" decimal NOT NULL
);

CREATE TABLE "Schedule" (
  "day" date,
  "shift" CHAR(9),
  "username" VARCHAR(30),
  PRIMARY KEY ("day", "shift")
);

CREATE TABLE "Revenue" (
  "day" date PRIMARY KEY,
  "income" decimal DEFAULT 0
);

ALTER TABLE "Sessions" ADD FOREIGN KEY ("username") REFERENCES "User" ("username");

ALTER TABLE "Computers" ADD FOREIGN KEY ("username") REFERENCES "User" ("username");

ALTER TABLE "Sessions" ADD FOREIGN KEY ("host_id") REFERENCES "Computers" ("host_id");

ALTER TABLE "Orders" ADD FOREIGN KEY ("username") REFERENCES "User" ("username");

ALTER TABLE "OrderItems" ADD FOREIGN KEY ("order_id") REFERENCES "Orders" ("order_id");

ALTER TABLE "OrderItems" ADD FOREIGN KEY ("fdservice_id") REFERENCES "FDService" ("fdservice_id");

ALTER TABLE "Schedule" ADD FOREIGN KEY ("username") REFERENCES "User" ("username");

INSERT INTO "Computers" ("host_id", "ip_address", "configuration", "cost_perhour", "status")
VALUES 
(1, '192.168.1.1', 'Windows 17', 5000, 'offline'),
(2, '192.168.1.2', 'Windows 17', 5000, 'offline'),
(3, '192.168.1.3', 'Dual Xeon E5', 10000, 'offline'),
(4, '192.168.1.4', 'Dual Xeon E5', 10000, 'offline'),
(5, '192.168.1.5', 'Dual Xeon E5', 10000, 'offline'),
(6, '192.168.1.6', 'PC GAMING', 7000, 'offline'),
(7, '192.168.1.7', 'PC GAMING', 7000, 'offline');


INSERT INTO "FDService" ("fdservice_id", "name", "cost")
VALUES 
(1, 'Bottle of Water', 10000),
(2, 'Coffee', 25000),
(3, 'Tea', 25000),
(4, 'Fruit Juice', 35000),
(5, 'Snack', 10000),
(6, 'Sandwich', 25000),
(7, 'Instant Noodle', 20000),
(8, 'Chicken Nuggets', 40000),
(9, 'Munster Energy (Can)', 50000);


-- Inserting 5 customers
INSERT INTO "User" ("username", "name", "email", "phone_number", "role", "password", "balance")
VALUES 
('huuduc', 'Little Duc', 'huuduc@gmail.com', '12345678900', 'customer', 'beduc', 68686),
('customer1', 'Customer 1', 'customer1@example.com', '12345678901', 'customer', 'customer1_pass',32000),
('customer2', 'Customer 2', 'customer2@example.com', '12345678902', 'customer', 'customer2_pass',30000),
('customer3', 'Customer 3', 'customer3@example.com', '12345678903', 'customer', 'customer3_pass', 45000),
('customer4', 'Customer 4', 'customer4@example.com', '12345678904', 'customer', 'customer4_pass', 50000),
('customer5', 'Customer 5', 'customer5@example.com', '12345678905', 'customer', 'customer5_pass', 30000);

-- Inserting 5 staff
INSERT INTO "User" ("username", "name", "email", "phone_number", "role",  "password")
VALUES 
('longnh', 'Huang Long', 'longnh@example.com', '12345678910', 'staff',  'longnh'),
('linhdv', 'Wen Ling', 'lingdv@example.com', '12345678911', 'staff',  'linhdv'),
('staff1', 'Staff 1', 'staff1@example.com', '12345678912', 'staff',  'staff1_pass'),
('staff2', 'Staff 2', 'staff2@example.com', '12345678913', 'staff', 'staff2_pass'),
('staff3', 'Staff 3', 'staff3@example.com', '12345678914', 'staff', 'staff3_pass'),
('staff4', 'Staff 4', 'staff4@example.com', '12345678915', 'staff', 'staff4_pass'),
('staff5', 'Staff 5', 'staff5@example.com', '12345678916', 'staff', 'staff5_pass');

-- Inserting 2 admins
INSERT INTO "User" ("username", "name", "email", "phone_number", "role", "password")
VALUES 
('kazuma', 'Duy Sato', 'kzumaa@gmail.com', '12345678920', 'admin', 'kazuma'),
('tabeos', 'Trung Anh', 'tabeos@example.com', '12345678921', 'admin', 'tabeos');

-- Sample Revenue
INSERT INTO "Revenue" VALUES
  ('2024-05-25', 55555),
  ('2024-05-26', 75000),
  ('2024-05-27', 123456),
  ('2024-05-28', 255000),
  ('2024-05-29', 200000);

-- Sample Schedule for linhdv
INSERT INTO "Schedule" VALUES
  ('2024-05-31', '0800:1200', 'linhdv'),
  ('2024-06-05', '1300:1700', 'linhdv'),
  ('2024-06-06', '0900:1500', 'linhdv'),
  ('2024-06-07', '0900:1500', 'linhdv');
