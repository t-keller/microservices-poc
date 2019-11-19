insert into treatments (id, name) values (1, 'Newsletter');
insert into treatments (id, name) values (2, 'Events');
insert into treatments (id, name) values (3, 'Prospect');

insert into consents (id, optin, person_id, treatment_id) values (1, true, '1', 1);
insert into consents (id, optin, person_id, treatment_id) values (2, false, '1', 2);
insert into consents (id, optin, person_id, treatment_id) values (3, true, '2', 1);
