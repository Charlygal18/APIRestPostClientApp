# APIRestPostClientApp
A Java application with a POST Endpoint to create a client with some rules about Client name, Client Number Phone and Zip Code.

Script to create tables

DROP TABLE IF EXISTS clients;
CREATE TABLE clients (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(12) NOT NULL, age INT, zip_code VARCHAR(5));

DROP TABLE IF EXISTS client_phones;
CREATE TABLE client_phones(id INT AUTO_INCREMENT PRIMARY KEY, client_id INT NOT NULL, phone_number VARCHAR(14), FOREIGN KEY (client_id) REFERENCES clients(id));
