SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Message;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Channel;
DROP TABLE IF EXISTS Role;

CREATE TABLE Role (
	idRole INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE User(
	idUserINTEGER PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(100),
	password VARCHAR(100),
	nickname VARCHAR(100),
	civilite VARCHAR(10),
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	idRole INTEGER NOT NULL,
	CONSTRAINT `FK_User_Role` FOREIGN KEY (idRole) referenceS Role (idRole)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Channel(
	idChannel INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	description VARCHAR(400)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Message(
	idMessageINTEGER PRIMARY KEY AUTO_INCREMENT,
	content TEXT,
	date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	idUser INTEGER NOT NULL,
	idChannel INTEGER NOT NULL,
	CONSTRAINT `FK_Message_User` FOREIGN KEY (idUser) referenceS User (idUser),
	CONSTRAINT `FK_Message_Channel` FOREIGN KEY (idChannel) referenceS Channel (idChannel)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



/* Insertion des Roles */ 


INSERT INTO role(idRole, name) VALUES
(1, 'Administrateur');


INSERT INTO role(idRole, name) VALUES

(2, 'Utilisateur');

/* Insertion des Users */
INSERT INTO user(idUser, email, password, nickname, civilite, createdAt, updatedAt, idRole) VALUES
(1, 'admin@gmail.com', 'azeaze', 'Admin', 'Mr', '2020-05-30 14:05:49', '2020-05-30 14:05:49', 1);
INSERT INTO user(idUser, email, password, nickname, civilite, createdAt, updatedAt, idRole) VALUES
(2, 'user1@gmail.com', 'azeaze', 'User1', 'Mrs', '2020-05-30 14:05:49', '2020-06-03 12:47:03', 2);
INSERT INTO user(idUser, email, password, nickname, civilite, createdAt, updatedAt, idRole) VALUES(3, 'user2@gmail.com', 'azeaze', 'User2', 'Mr', '2020-06-03 13:18:11', '2020-06-03 13:19:08', 2);

/* Insertion des Channels */

INSERT INTO channel(idChannel, name, description) VALUES
(1, 'Main', 'In this channel you can discuss of everything you want');

INSERT INTO channel(idChannel, name, description) VALUES
(2, 'interactive tities', 'On parle de trucs');



/* Insertion des Messages */ 
INSERT INTO message(idMessage, content, date, idUser, idChannel) VALUES
(1, 'Hello world ! =D', '2020-06-03 10:12:07', 1, 1)
;
INSERT INTO message(idMessage, content, date, idUser, idChannel) VALUES(2, 'Goodbye world ! =(', '2020-06-03 10:12:45', 2, 1);



SET FOREIGN_KEY_CHECKS = 1;