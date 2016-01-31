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
	
INSERT INTO foody.subcategory (subcategory_id, subcategory_name, category_id, created_date, lastmodified_date)
	VALUES (4, 'Spaghete Carbonara', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO foody.subcategory (subcategory_id, subcategory_name, category_id, created_date, lastmodified_date)
	VALUES (5, 'Spaghete Carbonara', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.subcategory (subcategory_id, subcategory_name, category_id, created_date, lastmodified_date)
	VALUES (6, 'Spaghete Milanese', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO foody.subcategory (subcategory_id, subcategory_name, category_id, created_date, lastmodified_date)
	VALUES (7, 'Spaghete Milanese', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--

INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (1, 'Piept pui la gratar cu legume', 1, 1, 1, 27, 'piept de pui, rosii, ciuperci, vinete', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (2, 'Piept pui la gratar', 1, 1, 2, 16, 'piept de pui, sos special', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (3, 'Chicken Sandwich', 1, 1, 3, 28, ' sandwich cu piept de pui la grătar', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (4, 'Spaghete Milanese', 1, 6, 2, 13, 'paste, sunca, ciuperci, parmezan, sos rosu', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (5, 'Spaghete Milanese', 3, 7, 2, 13, 'paste, sunca, ciuperci, parmezan, sos rosu', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (6, 'Spaghete Carbonara', 1, 4, 2, 13, 'paste, bacon, smantana, ou, parmezan', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (7, 'Spaghete Carbonara', 3, 5, 2, 13, 'paste, bacon, smantana, ou, parmezan', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (8, 'SPAGHETTI „MILANEZE”', 1, 6, 1, 13, 'spaghete, ciuperci, şuncă, sos roşii, ulei măsline, busuioc, parmezan', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (9, 'SPAGHETTI „MILANEZE”', 3, 7, 1, 13, 'spaghete, ciuperci, şuncă, sos roşii, ulei măsline, busuioc, parmezan', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (10, 'SPAGHETTI „CARBONARA”', 1, 4, 1, 28, 'spaghete, costiţă, ou, parmezan, smântână, ulei măsline', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO foody.menu_items (item_id, item_name, category_id, subcategory_id, restaurant_id, price_ron, description, photo_link, created_date, lastmodified_date)
	VALUES (11, 'SPAGHETTI „CARBONARA”', 3, 5, 1, 28, 'spaghete, costiţă, ou, parmezan, smântână, ulei măsline', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);