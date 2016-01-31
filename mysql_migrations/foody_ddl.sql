CREATE DATABASE `foody` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE IF NOT EXISTS foody.restaurants (
id INT(11) UNSIGNED PRIMARY KEY,
name VARCHAR(256) NOT NULL,
phone VARCHAR(20) DEFAULT "",
address VARCHAR(256) DEFAULT "",
weblink VARCHAR(1000) DEFAULT "",
created_date TIMESTAMP,
lastmodified_date TIMESTAMP
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS foody.subcategory (
subcategory_id INT(11) UNSIGNED PRIMARY KEY,
subcategory_name VARCHAR(256) NOT NULL,
category_id INT(11) NOT NULL,
created_date TIMESTAMP,
lastmodified_date TIMESTAMP
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS foody.category (
category_id INT(11) UNSIGNED PRIMARY KEY,
category_name VARCHAR(256) NOT NULL,
created_date TIMESTAMP,
lastmodified_date TIMESTAMP
) ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS foody.menu_items (
item_id INT(11) UNSIGNED,
item_name VARCHAR(256) NOT NULL,
category_id INT(11) UNSIGNED NOT NULL,
subcategory_id INT(11) UNSIGNED NOT NULL,
restaurant_id INT(11) UNSIGNED NOT NULL,
price_ron varchar(10) DEFAULT "0.1",
description varchar(1000) DEFAULT "",
photo_link VARCHAR(1000) DEFAULT "",
created_date TIMESTAMP,
lastmodified_date TIMESTAMP,
PRIMARY KEY (`item_id`),
CONSTRAINT FOREIGN KEY (`restaurant_id`) REFERENCES `foody`.`restaurants` (`id`),
CONSTRAINT FOREIGN KEY (`category_id`) REFERENCES `foody`.`category` (`category_id`),
CONSTRAINT FOREIGN KEY (`subcategory_id`) REFERENCES `foody`.`subcategory` (`subcategory_id`)
) ENGINE=INNODB; 

CREATE USER 'foody'@'localhost' IDENTIFIED BY '1234';

GRANT SELECT,UPDATE,DELETE ON foody.* TO 'foody'@'localhost';