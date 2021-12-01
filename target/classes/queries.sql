USE Building_company;

/*Выводим все данные из всех столбцов таблицы Addresses*/
SELECT * FROM Addresses;

/*Выводим все данные из столбцов first_name, last_name из таблицы Clients*/
SELECT first_name, last_name FROM Clients;

/*Columns alias. Выводим все значения со столбцов number, started_at, ended_at и меняем имена этих столбцов 
на order_number, start_date, end_date соответственно, в таблице Orders*/
SELECT number AS order_number, started_at AS start_date, ended_at AS end_date FROM Orders;

/*Использование table alias, а также сортируем вывод по имени*/
SELECT e.first_name, e.last_name FROM Employees e ORDER BY e.first_name;

/*Выводим значения из столбцов employee_id, personnel_number из таблицы Pay_sheets, тех, у кого значения
в hours_worked больше либо равно 100*/
SELECT employee_id, personnel_number FROM Pay_sheets WHERE hours_worked >= 100;

/*Использование вложенных запросов. Достаем все message из таблицы Feedbacks и выбираем тот message у которого
client_id будет равен имени Alexander в таблице Clients*/
SELECT message FROM Feedbacks WHERE client_id IN (SELECT id FROM Clients WHERE first_name = 'Alexander');

/*Выводим все номера заказов, кроме номера 2014, даты их окончания, 
а также имена и фамилии клиентов на которые эти заказы оформлены*/
SELECT o.number, o.ended_at, c.first_name, c.last_name 
FROM Orders o LEFT JOIN Clients c
ON o.client_id = c.id
WHERE o.number <> 2014;

/*Выводим количество отзывов и название компаний у которых количество отзывов больше одного*/ 
SELECT COUNT(f.id) AS Feedbacks_count, c.name
FROM Building_companies c LEFT JOIN Feedbacks f
ON c.id = f.company_id
LEFT JOIN Clients cl
ON f.client_id = cl.id
GROUP BY c.name HAVING Feedbacks_count > 1;

/*Получаем результирующую таблицу, которая объединила в себе Clients и Employees, результат сортируем
по фамилии в обратном алфавитном порядке*/
(SELECT id, company_id, first_name, last_name FROM Clients)
UNION ALL
(SELECT id, company_id, first_name, last_name FROM Employees)
ORDER BY last_name DESC;

/*Объединяем таблицы Addresses, Clients, Feedbacks и находим адрес клиента живущего в Бресте и его отзыв,
который состоит из четырех слов(если считать что слова отделяются пробелами)*/
SELECT a.city, a.street, a.house_number, a.apartment_number, c.first_name, c.last_name, f.message
FROM Addresses a INNER JOIN Clients c
ON a.id = c.address_id
INNER JOIN Feedbacks f
ON c.id = f.client_id
WHERE city = 'Brest' AND message NOT LIKE "% % % % %";

/*В таблице Addresses устанавливаем значение Andreevskaya в столбце street,где id = 2*/
UPDATE Addresses SET street = 'Andreevskaya' WHERE id = 2;

/*Отключаем режим безопасных обновлений на время сеанса и устанавливаем значение '13' в столбце
house_number в той же строке где street = 'Andreevskaya'*/
SET SQL_SAFE_UPDATES = 0;
UPDATE Addresses SET house_number = '13' WHERE street = 'Andreevskaya';

/*В таблице Pay_sheets увеличиваем всем оплату за час на 0.50 и округляем до двух знаков после запятой*/
UPDATE Pay_sheets SET payment_rubles_per_hour =ROUND((payment_rubles_per_hour + '0.50'),2);

/*Меняем значение в двух столбцах first_name и last_name в строке где date_of_birth = '01-06-1990'*/
UPDATE Employees SET first_name = 'Fedor', last_name = 'Konuhov' WHERE date_of_birth = STR_TO_DATE('01-06-1990', '%d-%m-%Y');

/*В таблице Pay_sheets в столбце payment_rubles_per_hour с максимальным значением отнимаем 0.10*/
UPDATE Pay_sheets SET payment_rubles_per_hour = (SELECT MAX(payment_rubles_per_hour - '0.10'));

/*В таблице Materials в строке где name = 'drywall' прибавляем к количеству 10, а всем остальным к остатку прибавляем 20*/
UPDATE Materials SET quantity = CASE WHEN name = 'drywall' THEN quantity = quantity + 10 ELSE quantity = quantity + 20 END;

/*Меняем дату в столбце created_at таблицы Feedbacks*/
UPDATE Feedbacks SET created_at = STR_TO_DATE('01-11-2021', '%d-%m-%Y') WHERE created_at = STR_TO_DATE('22-07-2019', '%d-%m-%Y');

/*Изменили сообщение в строке удовлетворяющей двум условиям*/
UPDATE Feedbacks SET message = 'The result is bad' WHERE company_id = 46 AND client_id = '2';

/*Изменили все цены, округлили значения до двух знаков после запятой, также поменяли дату обновления цен на услуги*/
UPDATE Services SET price =ROUND((price * 1.2),2), modified_at = STR_TO_DATE('24-11-2021', '%d-%m-%Y');

/*Изменение данных сразу в нескольких таблицах*/
UPDATE Clients c, Employees e SET c.first_name = 'Daniil', e.first_name = 'Alexandra' 
WHERE c.first_name = 'Vladimir'AND e.first_name = 'Alexander';

/*Удалить строку из таблицы Addresses где id = 1*/
DELETE FROM Addresses WHERE id = 1;

/*Удалить все данные из таблицы Addresses*/
DELETE FROM Addresses;

/*Удалить из Feedbacks все строки где есть ID который есть в Building_companies*/
DELETE FROM Feedbacks WHERE company_id IN (SELECT id FROM Building_companies);

/*Удалить из Pay_sheets все строки где hours_worked < 100*/
DELETE FROM Pay_sheets WHERE hours_worked < 100;

/*Удалить из Building_companies организацию с названием 'Mopid'*/
DELETE FROM Building_companies WHERE name = 'Mopid';

/*Удаление служащего по дате рождения*/
DELETE FROM Employees WHERE date_of_birth = STR_TO_DATE('25-12-1993', '%d-%m-%Y');

/*Удаление всех служащих у которых id не равен 5*/
DELETE FROM Employees WHERE id <> 5;

/*Удаляем строки в которых id = employee_id*/
DELETE FROM Pay_sheets WHERE id = employee_id;

/*Удаляем строки в которых payment_rubles_per_hour > 4*/
DELETE FROM Pay_sheets WHERE payment_rubles_per_hour > 4;

/*Удаляем строки в которых name начинается с В*/
DELETE FROM Services WHERE name LIKE 'B%';


