/*Врачи*/
INSERT INTO doctor(id, firstname, lastname, patronymic, specialization)
VALUES (0, 'Иван', 'Иванов', '', 'therapist');
INSERT INTO doctor(id, firstname, lastname, patronymic, specialization)
VALUES (1, 'Евгений', 'Петросян', 'Ваганович', 'okulist');
INSERT INTO doctor(id, firstname, lastname, patronymic, specialization)
VALUES (2, 'Мария', 'Шарапова', '', 'surgeon');

/*Пациенты*/
INSERT INTO patient(id, firstname, lastname, patronymic, phone)
VALUES (0, 'Петр', 'Петров', '', '8(845)125-54-48');
INSERT INTO patient(id, firstname, lastname, patronymic, phone)
VALUES (1, 'Петр', 'Абрамович', '', '8(233)456-34-78');
INSERT INTO patient(id, firstname, lastname, patronymic, phone)
VALUES (2, 'Эдуард', 'Шаляпин', '', '8(845)386-27-85');
INSERT INTO patient(id, firstname, lastname, patronymic, phone)
VALUES (3, 'Алла', 'Пугачева', '', '8(845)347-01-01');

/*Выписанные рецепты*/
INSERT INTO recipe(id, description, patient_id, doctor_id, created, priority)
VALUES (0, 'Vitamin C', 0, 0, '2017-11-15 00:00:00', 'norm');
INSERT INTO recipe(id, description, patient_id, doctor_id, created, priority)
VALUES (1, 'Настойка боярышника', 1, 2, '2017-10-15 00:00:00', 'norm');
INSERT INTO recipe(id, description, patient_id, doctor_id, created, priority)
VALUES (2, 'Аспирин', 2, 1, '2017-11-09 00:00:00', 'cito');
INSERT INTO recipe(id, description, patient_id, doctor_id, created, priority)
VALUES (3, 'Валидол', 1, 1, '2017-09-25 00:00:00', 'norm');
INSERT INTO recipe(id, description, patient_id, doctor_id, created, priority)
VALUES (4, 'Клизма', 2, 2, '2017-12-15 00:00:00', 'cito');
INSERT INTO recipe(id, description, patient_id, doctor_id, created, priority)
VALUES (5, 'Vitamin C', 3, 0, '2017-10-05 00:00:00', 'norm');
INSERT INTO recipe(id, description, patient_id, doctor_id, created, priority)
VALUES (6, 'Мазь вишневского', 3, 2, '2017-11-20 00:00:00', 'statim');