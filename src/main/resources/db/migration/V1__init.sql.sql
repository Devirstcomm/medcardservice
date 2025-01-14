CREATE TABLE disease
(
    id               INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    patient_id       INTEGER                                  NOT NULL,
    mkb10_dictionary VARCHAR(255)                             NOT NULL,
    start_date       date                                     NOT NULL,
    end_date         date,
    prescriptions    VARCHAR(1024)                            NOT NULL,
    CONSTRAINT pk_disease PRIMARY KEY (id)
);

CREATE TABLE mkb10_dictionary
(
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_mkb10_dictionary PRIMARY KEY (code)
);

CREATE TABLE patient
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name    VARCHAR(30)                              NOT NULL,
    last_name     VARCHAR(70)                              NOT NULL,
    patronymic    VARCHAR(255),
    birth_date    date                                     NOT NULL,
    gender        VARCHAR(255)                             NOT NULL,
    policy_number VARCHAR(255)                             NOT NULL,
    CONSTRAINT pk_patient PRIMARY KEY (id)
);

ALTER TABLE patient
    ADD CONSTRAINT uc_patient_policy_number UNIQUE (policy_number);

ALTER TABLE disease
    ADD CONSTRAINT FK_DISEASE_ON_MKB10_DICTIONARY FOREIGN KEY (mkb10_dictionary) REFERENCES mkb10_dictionary (code);

ALTER TABLE disease
    ADD CONSTRAINT FK_DISEASE_ON_PATIENT FOREIGN KEY (patient_id) REFERENCES patient (id);

-- Значения для заполнения таблицы Patient
INSERT INTO patient (first_name, last_name, patronymic, birth_date, gender, policy_number)
VALUES ('Александр', 'Иванов', 'Сергеевич', '1985-04-15', 'MALE', '1234567890123456'),
       ('Мария', 'Петрова', 'Александровна', '1990-08-25', 'FEMALE', '2345678901234567'),
       ('Екатерина', 'Смирнова', 'Игоревна', '1988-02-10', 'FEMALE', '3456789012345678'),
       ('Дмитрий', 'Кузнецов', null, '1975-12-30', 'MALE', '4567890123456789'),
       ('Анна', 'Сидорова', 'Петровна', '1992-11-22', 'FEMALE', '5678901234567890'),
       ('Сергей', 'Михайлов', null, '1980-03-03', 'MALE', '6789012345678901'),
       ('Ольга', 'Федорова', 'Владимировна', '1989-07-07', 'FEMALE', '7890123456789012'),
       ('Игорь', 'Николаев', null, '1995-09-29', 'MALE', '8901234567890123'),
       ('Наталья', 'Алексеева', 'Ильинична', '1982-05-18', 'FEMALE', '9012345678901234'),
       ('Виктор', 'Ковалев', null, '1972-01-01', 'MALE', '0123456789012345');

-- Значения для заполнения таблицы Disease
INSERT INTO disease (start_date, end_date, prescriptions, patient_id, mkb10_dictionary)
VALUES ('2023-01-10', NULL, 'Прием антибиотиков', 1, 'A00'),
       ('2023-02-15', '2023-02-20', 'Назначение: дополнительные анализы', 1, 'A01'),
       ('2023-03-01', NULL, 'Консультация специалиста', 2, 'A02'),
       ('2023-01-22', NULL, 'Отдых, обильное питье', 3, 'A03'),
       ('2023-03-15', '2023-04-01', 'Курс антибиотиков', 4, 'A15'),
       ('2023-04-10', NULL, 'Использование противовирусных препаратов', 5, 'A01'),
       ('2023-01-01', NULL, 'Другие назначения', 6, 'A00'),
       ('2023-02-01', '2023-02-28', 'Лечение симптомов', 7, 'A02'),
       ('2023-03-20', '2023-03-25', 'Контроль и наблюдение', 8, 'A03'),
       ('2023-01-15', NULL, 'Рекомендации по питанию', 9, 'A15'),
       ('2023-02-20', '2023-03-05', 'Специальная терапия', 10, 'A00');
