CREATE DATABASE homework;

CREATE SCHEMA messenger AUTHORIZATION postgres;

CREATE TABLE "messenger".user
(
	login CHARACTER VARYING NOT NULL UNIQUE CHECK (login != ''),
	password CHARACTER VARYING NOT NULL CHECK (password !=''),
	firstName CHARACTER VARYING NOT NULL CHECK (firstName != ''),
	lastName CHARACTER VARYING NOT NULL CHECK (lastName != ''),
	birthday DATE,
	CONSTRAINT user_id PRIMARY KEY (login)
)

CREATE TABLE "messenger".message
(
	toLogin CHARACTER VARYING,
	fromLogin CHARACTER VARYING,
	dateTime TIMESTAMP WITHOUT TIME ZONE,
	"message" CHARACTER VARYING,
	CONSTRAINT toLogin FOREIGN KEY (toLogin) REFERENCES "messenger".user (login),
	CONSTRAINT fromLogin FOREIGN KEY (fromLogin) REFERENCES "messenger".user (login)
)

INSERT INTO "messenger".user (login, password, firstName, lastName, birthday) VALUES ('Alex', 123, 'Alexey', 'Popov', '2002-01-01');
INSERT INTO "messenger".user (login, password, firstName, lastName, birthday) VALUES ('Vic', 123, 'Victor', 'Gonchar', '1996-01-21');
INSERT INTO "messenger".message (toLogin, date)

SELECT * FROM "messenger".user;
SELECT * FROM "messenger".message;

SELECT * FROM "messenger".user WHERE login = 'Alex' AND password = '123';
SELECT * FROM "messenger".user WHERE password = '123';
SELECT login FROM "messenger".user WHERE login = 'Alex';

--DROP TABLE "messenger".person;
--DROP TABLE "messenger".message;
