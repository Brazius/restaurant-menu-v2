INSERT INTO Products(title, description, price) VALUES('Kebabas', 'pupelės, kopustai, svogunai, pomidorai, padažas pasirinktinai', 3.50);
INSERT INTO Products(title, description, price) VALUES('Pica', 'keturiu suriu gaminys, kupis ananasai, silke', 8.50);
INSERT INTO Products(title, description, price) VALUES('Čili sriuba', 'Čili pipirai,pupeles, sojos faršas, ', 12.50);
INSERT INTO Products(title, description, price) VALUES('Kepta duona', 'Kaimiška kepta duona intirnta česnakų, druska, garstyčios', 1.50);

INSERT INTO Users(user_id, username, password, name, last_name)
    VALUES(1, 'user', '{bcrypt}$2y$12$A7x.2lPxE6YdV8ed6OYbDucRiod32wqMF9JNerE.wq4glQWaIjRnO', 'Useris', 'Userskis');
INSERT INTO Users(user_id, username, password, name, last_name)
    VALUES(2, 'admin', '{bcrypt}$2y$12$A7x.2lPxE6YdV8ed6OYbDucRiod32wqMF9JNerE.wq4glQWaIjRnO', 'Adminas', 'Gestapaskauskas');

INSERT INTO Roles(role_id, role) VALUES(1, 'CUSTOMER');
INSERT INTO Roles(role_id, role) VALUES(2, 'ADMIN');

INSERT INTO Users_Roles(user_id, role_id) VALUES(1, 1);
INSERT INTO Users_Roles(user_id, role_id) VALUES(2, 2);
