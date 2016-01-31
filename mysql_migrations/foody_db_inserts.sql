INSERT INTO foody.restaurants (id, name, phone, address, weblink, created_date, lastmodified_date) 
	VALUES (1, 'Azzuro', '0761308015', 'Splaiul Independenței 210-210B', 'http://www.azzurro.com.ro/en/azzurro-orhideea/', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.restaurants (id, name, phone, address, weblink, created_date, lastmodified_date) 
	VALUES (2, 'Roxy Pub', '0761308015', 'Splaiul Independenței 290', 'http://roxy-pub.ro', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.restaurants (id, name, phone, address, weblink, created_date, lastmodified_date) 
	VALUES (3, 'Vacamuuu', '0761308015', 'CALEA FLOREASCA NO.111', 'http://www.vacamuuu.com/', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--

INSERT INTO foody.category (category_id, category_name, created_date, lastmodified_date)
	VALUES (1, 'Romanian', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO foody.category (category_id, category_name, created_date, lastmodified_date)
	VALUES (2, 'Chinese', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO foody.category (category_id, category_name, created_date, lastmodified_date)
	VALUES (3, 'Italian', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO foody.category (category_id, category_name, created_date, lastmodified_date)
	VALUES (4, 'Indian', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 

INSERT INTO foody.subcategory (subcategory_id, subcategory_name, category_id, created_date, lastmodified_date)
	VALUES (1, 'Piept pui la gratar', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.subcategory (subcategory_id, subcategory_name, category_id, created_date, lastmodified_date)
	VALUES (2, 'Salata Ceasar', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.subcategory (subcategory_id, subcategory_name, category_id, created_date, lastmodified_date)
	VALUES (3, 'Pizza Capriciosa', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


--

INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (1, 'Piept pui la gratar cu legume', 1, 1, 1, 27, 'piept de pui, rosii, ciuperci, vinete', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (2, 'Piept pui la gratar', 1, 1, 2, 16, 'piept de pui, sos special', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);