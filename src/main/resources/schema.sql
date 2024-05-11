CREATE TABLE IF NOT EXISTS stores
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   category_id INT NOT NULL,
   name VARCHAR (50) NOT NULL,
   image_name VARCHAR (255),
   description VARCHAR (255) NOT NULL,
   open_time VARCHAR (255) NOT NULL,
   close_time VARCHAR (255) NOT NULL,
   amount INT NOT NULL,
   postal_code VARCHAR (50) NOT NULL,
   address VARCHAR (255) NOT NULL,
   phone_number VARCHAR (50) NOT NULL,
   closed_days VARCHAR (50) NOT NULL,
   created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS roles
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR (50) NOT NULL,
   namae VARCHAR (50) NOT NULL
);
CREATE TABLE IF NOT EXISTS users
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR (50) NOT NULL,
   email VARCHAR (255) NOT NULL UNIQUE,
   password VARCHAR (255) NOT NULL,
   role_id INT NOT NULL,
   enabled BOOLEAN NOT NULL,
   subscid VARCHAR (255) NULL,
   created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS category
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR (50) NOT NULL
);
CREATE TABLE IF NOT EXISTS reservations
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   store_id INT NOT NULL,
   user_id INT NOT NULL,
   reservation_date DATE NOT NULL,
   number_of_people INT NOT NULL,
   created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (store_id) REFERENCES stores (id),
   FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS review (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
userid  INT NOT NULL,
storeid INT NOT NULL,
value INT NOT NULL,
commenttext VARCHAR(255) NOT NULL,
updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (userid) REFERENCES users (id),
FOREIGN KEY (storeid) REFERENCES stores (id)
);

 CREATE TABLE IF NOT EXISTS favorite(
 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 user_id INT NOT NULL,
 store_id INT NOT NULL,
 created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
 updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES users (id),
FOREIGN KEY (store_id) REFERENCES stores (id)
);
 

 