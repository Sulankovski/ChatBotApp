alter table public."user" add column email text unique;
alter table public."user" add column hash_password text;