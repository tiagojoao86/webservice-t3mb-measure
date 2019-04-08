create sequence measure_user_seq start 1;
create sequence user_group_seq start 1;
create sequence user_role_seq start 1;
create sequence sysfunction_seq start 1;
create table measure_user (
	id int primary key,
	name varchar(255) not null,
	login varchar(255) UNIQUE not null,
	password varchar(255) not null,
	userGroup_ID int not null,
	superior_ID int,
	hasSuperior boolean,
	status varchar(30)	
);

create table user_group (
	id int primary key,
	name varchar(50)
);

alter table measure_user 
add constraint user_group_fk foreign key (userGroup_id) 
references user_group (id);

create table user_role (
	id int primary key,
	name varchar(50)
);

create table sysfunction (
	id int primary key,
	name varchar(50),
	url varchar(255)
);

create table user_roles (
	id_user int,
	id_role int
);
alter table user_roles 
add constraint user_roles_pk primary key (id_user, id_role);

alter table user_roles
add constraint user_roles_user_fk foreign key (id_user)
references measure_user (id);

alter table user_roles
add constraint user_roles_role_fk foreign key (id_role)
references user_role (id);

create table role_sysfunctions (
	id_role int,
	id_sysfunction int
);
alter table role_sysfunctions
add constraint role_sysfunctions_pk primary key (id_role, id_sysfunction);

alter table role_sysfunctions
add constraint role_sysfunctions_role_fk foreign key (id_role)
references user_role (id);

alter table role_sysfunctions
add constraint role_sysfunctions_sysfunction_fk foreign key (id_sysfunction)
references sysfunction (id);

create table sysfunction_subfunctions (
	id_sysfunction int,
	id_subfunction int,
	primary key (id_sysfunction, id_subfunction)
);

alter table sysfunction_subfunctions
add constraint sysfunction_subfunctions_sysfunction_fk 
foreign key (id_sysfunction)
references sysfunction (id);

alter table sysfunction_subfunctions
add constraint sysfunction_subfunctions_subfunction_fk 
foreign key (id_subfunction)
references sysfunction (id);