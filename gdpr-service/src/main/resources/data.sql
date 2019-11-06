insert into treatments (id, name) values (1, 'Newsletter');
insert into treatments (id, name) values (2, 'Events');
insert into treatments (id, name) values (3, 'Prospect');

insert into consents (id, optin, person_id, treatment_id) values (1, true, '6c30be64-846d-45ba-8d19-f78b8987fb0f', 1);
insert into consents (id, optin, person_id, treatment_id) values (2, false, '6c30be64-846d-45ba-8d19-f78b8987fb0f', 2);
insert into consents (id, optin, person_id, treatment_id) values (3, true, 'db7d5732-df83-4f89-ad71-829340f23dce', 1);
