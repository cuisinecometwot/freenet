drop database if exists cybercafe;
create database cybercafe;
\c cybercafe


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


INSERT INTO "Computers" ("host_id", "ip_address", "configuration", "cost_perhour", "status")
VALUES 
(1, '192.168.1.1', 'Config1', 5000, 'offline'),
(2, '192.168.1.2', 'Config2', 5000, 'offline'),
(3, '192.168.1.3', 'Config3', 5000, 'offline'),
(4, '192.168.1.4', 'Config4', 5000, 'offline'),
(5, '192.168.1.5', 'Config5', 5000, 'offline'),
(6, '192.168.1.6', 'Config6', 5000, 'offline'),
(7, '192.168.1.7', 'Config7', 5000, 'offline');


INSERT INTO "FDService" ("fdservice_id", "name", "cost")
VALUES 
(1, 'Water', 10000),
(2, 'Coffee', 25000),
(3, 'Tea', 15000),
(4, 'Fruit Juice', 35000),
(5, 'Snack', 20000),
(6, 'Sandwich', 45000),
(7, 'Salad', 30000);



-- Inserting 5 customers
INSERT INTO "User" ("username", "name", "email", "phone_number", "role", "password","balance")
VALUES 
('kzumaa', 'Duy', 'kzumaa@gmail.com', '12345678901', 'customer', 'kzumaa', 50000),
('customer2', 'Customer Two', 'customer2@example.com', '12345678902', 'customer', 'customer2_pass',30000),
('customer3', 'Customer Three', 'customer3@example.com', '12345678903', 'customer', 'customer3_pass', 45000),
('customer4', 'Customer Four', 'customer4@example.com', '12345678904', 'customer', 'customer4_pass', 50000),
('customer5', 'Customer Five', 'customer5@example.com', '12345678905', 'customer', 'customer5_pass', 30000);

-- Inserting 5 staff
INSERT INTO "User" ("username", "name", "email", "phone_number", "role",  "password")
VALUES 
('staff1', 'Staff One', 'staff1@example.com', '12345678906', 'staff',  'staff1_pass'),
('staff2', 'Staff Two', 'staff2@example.com', '12345678907', 'staff', 'staff2_pass'),
('staff3', 'Staff Three', 'staff3@example.com', '12345678908', 'staff', 'staff3_pass'),
('staff4', 'Staff Four', 'staff4@example.com', '12345678909', 'staff', 'staff4_pass'),
('staff5', 'Staff Five', 'staff5@example.com', '12345678910', 'staff', 'staff5_pass');

-- Inserting 2 admins
INSERT INTO "User" ("username", "name", "email", "phone_number", "role", "password")
VALUES 
('kazuma', 'Duy V', 'kazuma@example.com', '12345678911', 'admin', 'kazuma'),
('tabeos', 'Trung Anh', 'tabeos@example.com', '12345678912', 'admin', 'tabeos');