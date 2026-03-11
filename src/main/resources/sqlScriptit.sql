DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS app_user;

CREATE TABLE category (
	categoryid BIGSERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

INSERT INTO category (name)
VALUES
('Fiction'),
('Horror'),
('Drama'),
('Novell');


CREATE TABLE book (
	id BIGSERIAL PRIMARY KEY,
	title VARCHAR(150) NOT NULL,
	author VARCHAR(150) NOT NULL,
	publication_year INT,
	isbn VARCHAR(20) NOT NULL,
	price BIGINT,
	categoryid BIGINT REFERENCES category(categoryid)
);

INSERT INTO book (title, author, publication_year, isbn, price, categoryid)
VALUES
('Jäniksen vuosi', 'Arto Paasilinna', 1975, '9780132350884', 45, 4),
('Ihmisen lyhyt historia', 'Yuval Noah Harar', 2011, '9789522792310', 55, 4);

CREATE TABLE app_user (
id BIGSERIAL PRIMARY KEY,
username VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(100) NOT NULL,
role VARCHAR(50) NOT NULL
);

INSERT INTO app_user (username, password, role)
VALUES
('user', '$2a$12$uTuY.EhLmHPX.zejOfbPKOvs8E8A8SYyM70BZ.yyet9DXSeLHiBOm', 'USER'),
('admin', '$2a$12$M9Yl/Nb2vne/zWcs.RGL2eIIRX5VlfkUZyS0K1OlYvNAJWBUryrDy', 'ADMIN');

