  create table if not exists user (
	idUser int(8) NOT NULL AUTO_INCREMENT,
	username varchar(30) NOT NULL,
	password varchar(300) NOT NULL,
	role varchar(15) NOT NULL,
	email varchar(50) NOT NULL,
	organization int(8) NOT NULL,
	PRIMARY KEY(idUser)
);

create table if not exists task (
	idTask int(8) NOT NULL AUTO_INCREMENT,
	xmlFile varchar(60000) NOT NULL,
	stateOfTask varchar(50) NOT NULL,
	progressOfTask int(20) NOT NULL,
	ifpublic tinyint(1) NOT NULL,
	eta int(8) NOT NULL,
	name varchar(300) NOT NULL,
	user int(8)NOT NULL,
	organization int(8) NOT NULL,
	PRIMARY KEY(idTask)
);

create table if not exists organization(
	idOrganization int(8) NOT NULL AUTO_INCREMENT,
	nameOfOrganization varchar(50) NOT NULL,
	PRIMARY KEY(idOrganization)
);

alter table task add foreign key (user) references user(idUser);
alter table user add foreign key  (organization) references organization(idOrganization);
alter table task add foreign key (organization) references organization(idOrganization);