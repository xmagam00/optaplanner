  create table if not exists user (
	id_user int(8) NOT NULL AUTO_INCREMENT,
	user_name varchar(30),
	password varchar(30),
	role varchar(15) NOT NULL,
	email varchar(50) NOT NULL,
	organization int(8) NOT NULL,
	PRIMARY KEY(id_user)
);

create table if not exists task (
	id_task int(8) NOT NULL AUTO_INCREMENT,
	xml_file varchar(60000),
	state_of_task varchar(50) NOT NULL,
	progress_of_task int(20) NOT NULL,
	ifpublic tinyint(1),
	eta int(8),
	name varchar(10),
	user int(8),
	PRIMARY KEY(id_task)
);

create table if not exists organization(
	id_organization int(8) NOT NULL AUTO_INCREMENT,
	name_of_organization varchar(50),
	PRIMARY KEY(id_organization)
);

alter table task add foreign key (user) references user(id_user);
alter table user add foreign key  (organization) references organization(id_organization);