DELETE FROM `restaurant-web-app_db`.menu_products;
DELETE FROM `restaurant-web-app_db`.menu_categories;

INSERT INTO `restaurant-web-app_db`.menu_categories (id, active, name)
VALUES 
(1, 1, 'Breakfast'),
(2, 1, 'Salad'),
(3, 1, 'Pizza'),
(4, 1, 'Pasta'),
(5, 1, 'Fish'),
(6, 1, 'Beef'),
(7, 1, 'Chicken'),
(8, 1, 'Tacos');

INSERT INTO `restaurant-web-app_db`.menu_products (id, active, description, name, price, category_id)
VALUES
(1, 1, '2 Eggs, Chicken Ham, Mushrooms, Olives', 'English Breakfast', 150, 1),
(2, 1, 'Test, Test, Test', 'Test1', 120, 1),
(3, 1, '2 Eggs, Olives', 'Omlette', 130, 1),
(4, 1, 'Test, Test, Test', 'Test2', 120, 1),
(5, 1, 'Test, Test, Test', 'Test3', 120, 1),
(6, 1, 'Test, Test, Test', 'Test4', 120, 1),
(7, 1, 'Test, Test, Test', 'Test5', 120, 1),
(8, 1, 'Test, Test, Test', 'Test6', 120, 1),
(9, 1, 'Test, Test, Test', 'Test7', 150, 2),
(10, 1, 'Test, Test, Test', 'Test8', 120, 2),
(11, 1, 'Test, Test, Test', 'Test9', 120, 3),
(12, 1, 'Test, Test, Test', 'Test10', 150, 3),
(13, 1, 'Test, Test, Test', 'Test11', 120, 3);

