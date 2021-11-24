USE Building_company;

INSERT INTO Addresses(city, street, house_number, apartment_number) VALUES 
('Minsk', 'Voloha', '23', '2'), 
('Mogilev', 'Lenina', '15', '6'), 
('Brest', 'Yakuba Kolasa', '1A', '67'), 
('Vitebsk', 'Nezalezhnasci', '34/2', '12'), 
('Grodno', 'Pripyackaya', '77', '191');

INSERT INTO Building_companies(address_id, name) VALUES 
(5, 'Mopid'), 
(3, 'Stroyka'), 
(4, 'Sweet home'), 
(1, 'Building development'), 
(2, 'Roga i kopyta');

INSERT INTO Clients(address_id, company_id, first_name, last_name) VALUES
(1, 4, 'Alexander', 'Yagodkin'),
(3, 5, 'Veronica', 'Malinina'),
(4, 1, 'Polina', 'Lesnova'),
(2, 3, 'Vladimir', 'Boriskevicn'),
(5, 2, 'Olga', 'Danilovich');

INSERT INTO Feedbacks(company_id, client_id, message, created_at) VALUES
(50, 2, 'You did a good job, Im happy with the result', STR_TO_DATE('01-12-2020', '%d-%m-%Y')),
(48, 3, 'I was dissatisfied', STR_TO_DATE('30-01-2021', '%d-%m-%Y')),
(46, 4, 'Everything was done perfectly', STR_TO_DATE('22-07-2019', '%d-%m-%Y')),
(46, 2, 'The result is satisfied', STR_TO_DATE('13-06-2021', '%d-%m-%Y')),
(46, 1, 'I recommend to everyone, great company', STR_TO_DATE('20-10-2018', '%d-%m-%Y'));

INSERT INTO Orders(client_id, number, started_at, ended_at, responsible_person, modified_at) VALUES
(3, '2534', STR_TO_DATE('12-12-2020', '%d-%m-%Y'), STR_TO_DATE('31-12-2020', '%d-%m-%Y'), 'Kachanov A.I.', STR_TO_DATE('01-01-2021', '%d-%m-%Y')),
(5, '2014', STR_TO_DATE('01-02-2019', '%d-%m-%Y'), STR_TO_DATE('23-02-2019', '%d-%m-%Y'), 'Strebko M.L.', STR_TO_DATE('24-02-2019', '%d-%m-%Y')),
(2, '2532', STR_TO_DATE('16-06-2020', '%d-%m-%Y'), STR_TO_DATE('12-07-2020', '%d-%m-%Y'), 'Voronov P.V.', STR_TO_DATE('13-07-2020', '%d-%m-%Y')),
(4, '2048', STR_TO_DATE('03-07-2021', '%d-%m-%Y'), STR_TO_DATE('11-09-2021', '%d-%m-%Y'), 'Kachanov A.I', STR_TO_DATE('12-09-2021', '%d-%m-%Y')),
(1, '2112', STR_TO_DATE('30-09-2021', '%d-%m-%Y'), STR_TO_DATE('7-11-2021', '%d-%m-%Y'), 'Kachanov A.I', STR_TO_DATE('08-11-2021', '%d-%m-%Y'));

INSERT INTO Employees(company_id, first_name, last_name, date_of_birth) VALUES
(2, 'Anton', 'Mayorov', STR_TO_DATE('18-12-1985', '%d-%m-%Y')),
(3, 'Victor', 'Bakov', STR_TO_DATE('01-06-1990', '%d-%m-%Y')),
(5, 'Alexandra', 'Kalinina', STR_TO_DATE('14-01-1979', '%d-%m-%Y')),
(4, 'Evginiy', 'Gasanov', STR_TO_DATE('07-11-1981', '%d-%m-%Y')),
(1, 'Marina', 'Alinovich', STR_TO_DATE('27-07-1992', '%d-%m-%Y')),
(1, 'Alexander', 'Alinovich', STR_TO_DATE('25-12-1993', '%d-%m-%Y')),
(1, 'Sergey', 'Alinovich', STR_TO_DATE('10-05-1991', '%d-%m-%Y'));

INSERT INTO Employee_addresses(address_id, employee_id) VALUES
(2, 1),
(1, 3),
(4, 5),
(5, 2),
(3, 4);

INSERT INTO Pay_sheets(employee_id, personnel_number, hours_worked, payment_rubles_per_hour) VALUES
(3, '091647', '120', '2.16'),
(1, '091530', '128', '2.16'),
(5, '033147', '16', '3.48'),
(4, '099098', '140', '2.32'),
(2, '056797', '110', '3.02');

INSERT INTO Materials(type, name, quantity) VALUES
('Material', 'drywall', '3'),
('Material', 'hard putty', '25'),
('Material', 'whitewashing', '17'),
('Material', 'cement', '90'),
('Material', 'nail', '56');

INSERT INTO Warehouses(company_id, updated_at) VALUES
(1, STR_TO_DATE('01-08-2020', '%d-%m-%Y')),
(5, STR_TO_DATE('17-12-2020', '%d-%m-%Y')),
(2, STR_TO_DATE('13-02-2021', '%d-%m-%Y')),
(4, STR_TO_DATE('22-07-2021', '%d-%m-%Y')),
(3, STR_TO_DATE('23-11-2021', '%d-%m-%Y'));

INSERT INTO Warehouse_materials(material_id, warehouse_id) VALUES
(2, 5),
(3, 2),
(4, 1),
(1, 3),
(5, 4);

INSERT INTO Services(company_id, name, price, modified_at) VALUES
(1, 'Wall keying', '50.68', STR_TO_DATE('21-07-2021', '%d-%m-%Y')),
(2, 'Building', '399.99', STR_TO_DATE('13-08-2021', '%d-%m-%Y')),
(3, 'Demolition of walls', '20.03', STR_TO_DATE('18-09-2021', '%d-%m-%Y')),
(5, 'Euro repair', '180.37', STR_TO_DATE('01-10-2021', '%d-%m-%Y')),
(4, 'Сonsultation', '5.18', STR_TO_DATE('21-11-2021', '%d-%m-%Y'));
