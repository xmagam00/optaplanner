create if not exists user (
	id_user int(8) NOT NULL,
	user_name varchar2(30),
	password varchar2(30),
	name_of_organization varchar2(30),
	user_role int(8) NOT NULL,
	task int(8) NOT NULL,
    PRIMARY KEY(id_user)


);

create if not exists role (
	id_role int(8) NOT NULL,
	name_of_role varchar2(30),
	PRIMARY KEY(id_role)



);

create if not exists tasks (
	id_task int(8) NOT NULL,
	xml_file varchar2(60000),
	state_of_task int(8) NOT NULL,
	progress_of_task int(20) NOT NULL,
	url varchar2(30),
	PRIMARY KEY(id_task)
);

create if not exists state_task (
	id_task int(8) NOT NULL,
	state varchar2(30),
	PRIMARY KEY(id_task)
);

alter table user add foreign key user_role references role(id_role);
alter table user add foreign key task references task(id_task);
alter table task add foreign key state_of_task references state_task(id_task);