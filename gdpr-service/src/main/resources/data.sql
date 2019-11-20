insert into treatments (id, name) values (1, 'Newsletter');
insert into treatments (id, name) values (2, 'Events');
insert into treatments (id, name) values (3, 'Prospect');

insert into consents (optin, person_id, treatment_id) values (true, '1', 1);
insert into consents (optin, person_id, treatment_id) values (false, '1', 2);
insert into consents (optin, person_id, treatment_id) values (true, '2', 1);
