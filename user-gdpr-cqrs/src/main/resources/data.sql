insert into treatments (id, name) values (1, 'Newsletter') ON CONFLICT (id) DO NOTHING;
insert into treatments (id, name) values (2, 'Events') ON CONFLICT (id) DO NOTHING;
insert into treatments (id, name) values (3, 'Prospect') ON CONFLICT (id) DO NOTHING;
