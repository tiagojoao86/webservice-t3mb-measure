insert into user_group (id, name)
values (nextval('user_group_seq'), 'Administradores');
insert into user_group (id, name)
values (nextval('user_group_seq'), 'Coordenação');
insert into user_group (id, name)
values (nextval('user_group_seq'), 'Recepção');

insert into user_role (id, name)
values (nextval('user_role_seq'), 'Administrador');
insert into user_role (id, name)
values (nextval('user_role_seq'), 'Avaliador');
insert into user_role (id, name)
values (nextval('user_role_seq'), 'Avaliado');

insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Cadastros', '/registration');
insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Meus Avaliados', '/evaluations');
insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Minhas Avaliações', '/evaluations');
insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Usuários', '/registration/users');
insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Ciclos', '/registration/cycles');
insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Tipos de Avaliações', '/registration/evaluations-type');
insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Colaboradores', '/registration/collaborators');
insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Avaliações', '/evaluations/my-evaluated');
insert into sysfunction (id, name, url)
values (nextval('sysfunction_seq'),
		'Avaliações', '/evaluations/my-evaluations');
		
insert into sysfunction_subfunctions (id_sysfunction, id_subfunction)
values (1, 4);
insert into sysfunction_subfunctions (id_sysfunction, id_subfunction)
values (1, 5);
insert into sysfunction_subfunctions (id_sysfunction, id_subfunction)
values (1, 6);
insert into sysfunction_subfunctions (id_sysfunction, id_subfunction)
values (2, 7);
insert into sysfunction_subfunctions (id_sysfunction, id_subfunction)
values (2, 8);
insert into sysfunction_subfunctions (id_sysfunction, id_subfunction)
values (3, 9);

insert into role_sysfunctions (id_role, id_sysfunction)
values (1, 1);
insert into role_sysfunctions (id_role, id_sysfunction)
values (2, 2);
insert into role_sysfunctions (id_role, id_sysfunction)
values (3, 3);
select * from user_group;

insert into measure_user (id, name, login, password, usergroup_id, superior_id, hasSuperior, status)
values (nextval('measure_user_seq'), 'Administrador', 'admin', '123456', 1, null, false, 'A');
insert into measure_user (id, name, login, password, usergroup_id, superior_id, hasSuperior, status)
values (nextval('measure_user_seq'), 'Avaliador', 'avaliador', '123456', 2, null, false, 'A');
insert into measure_user (id, name, login, password, usergroup_id, superior_id, hasSuperior, status)
values (nextval('measure_user_seq'), 'Avaliado', 'avaliado', '123456', 3, 4, true, 'A');
select * from user_role;
select * from measure_user;
insert into user_roles(id_user, id_role)
values (3,1);
insert into user_roles(id_user, id_role)
values (4,2);
insert into user_roles(id_user, id_role)
values (4,3);
insert into user_roles(id_user, id_role)
values (5,3);