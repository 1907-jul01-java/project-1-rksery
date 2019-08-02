-- create transaction function to add users
--SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
--DROP VIEW IF EXISTS full_set;
--DROP VIEW IF EXISTS full_nopw;
DROP TABLE IF EXISTS artwork;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS permissions;
--DROP SEQUENCE IF EXISTS account_id_seq;
--create sequence account_id_seq increment 3 minvalue 100000 maxvalue 999999 start 100000;
-- CREATE
-- OR REPLACE FUNCTION random_between(low INT, high INT) RETURNS INT AS $$ BEGIN RETURN floor(random() * (high - low + 1) + low);
-- END;
--$$ language 'plpgsql' STRICT;
create table permissions (
  permid serial primary key,
  title text not null unique
);
create table users (
  userid serial not null primary key,
  username text not null unique,
  pw text not null,
  displayname text,
  permissions integer not null references permissions(permid)
);
create table artwork (
  artid integer references users(userid),
  title text not null,
  price money not null,
  filepath text not null primary key,
  approved integer not null default 0
);
create
or replace function get_user_id (user_name text) returns integer as $$
SELECT
  userid
from
  users
where
  username = user_name;
$$ language sql;
create
  or replace function get_user_perm (user_name text) returns integer as $$
SELECT
  permissions
from
  users
where
  username = user_name;
$$ language sql;
insert into
  permissions (title)
VALUES('Artist');
insert into
  permissions (title)
VALUES('Curator');
insert into
  permissions (title)
VALUES('Administrator');
-- insert into
  --   customerstatus (statusactive)
  -- VALUES
  --   ('New');
  -- insert into
  --   customerstatus (statusactive)
  -- VALUES
  --   ('Approved');
  -- insert into
  --   customerstatus (statusactive)
  -- VALUES
  --   ('Denied');
insert into
  users (username, pw, permissions)
VALUES
  ('rksery', 'password', 3);
-- insert into
  --   names (nameid, firstname, middlename, lastname)
  -- VALUES
  --   (
  --     get_user_id('rksery'),
  --     'Robert',
  --     'Keefer',
  --     'Sery'
  --   );
insert into
  users(username, pw, displayname, permissions)
VALUES
  ('obrist', 'password', 'Hans-Ulrich Obrist', 2);
-- insert into
  --   names (nameid, firstname, middlename, lastname)
  -- VALUES
  --   (get_user_id('jjdoe'), 'John', 'Jay', 'Doe');
insert into
  users (username, pw, displayname, permissions)
VALUES
  ('vangogh', 'password', 'Vincent van Gogh', 1);
-- insert into
  --   names (nameid, firstname, middlename, lastname)
  -- VALUES
  --   (
  --     get_user_id('testcust'),
  --     'My',
  --     'Test',
  --     'Customer'
  --   );
insert into
  artwork (artid, title, price, filepath)
VALUES
  (
    (
      select
        userid
      from
        users
      where
        username = 'vangogh'
    ),
    'Oleanders',
    12345,
    'DT1494.jpg'
  );
insert into
  users (username, pw, displayname, permissions)
VALUES
  ('kyosai', 'password', 'Kawanabe Kyosai', 1);
-- insert into
  --   names (nameid, firstname, middlename, lastname)
  -- VALUES
  --   (
  --     get_user_id('testcust2'),
  --     'My2',
  --     'Test2',
  --     'Customer2'
  --   );
insert into
  artwork (artid, title, price, filepath)
VALUES
  (
    (
      select
        userid
      from
        users
      where
        username = 'kyosai'
    ),
    'Fish in a Whirlpool',
    12346,
    'DP211790.jpg'
  );
-- create
  --   or replace function get_time () returns time with time zone as $$
  -- select
  --   current_time;
  -- $$ language sql;
  -- http://www.postgresqltutorial.com/postgresql-random-range/
  --   create
  --   or replace view full_set as
  -- select
  --   title,
  --   accountnumber,
  --   statusactive,
  --   username,
  --   pw,
  --   firstname,
  --   middlename,
  --   lastname,
  --   balance
  -- from
  --   permissions,
  --   users,
  --   names,
  --   customerstatus,
  --   customers
  -- where
  --   users.permissions = permissions.permid
  --   and names.nameid = users.userid
  --   and customers.custid = users.userid
  --   and customerstatus.statusid = customers.custactive;
  -- create
  --   or replace view full_nopw as
  -- select
  --   accountnumber,
  --   statusactive,
  --   username,
  --   firstname,
  --   middlename,
  --   lastname,
  --   balance
  -- from
  --   permissions,
  --   users,
  --   names,
  --   customerstatus,
  --   customers
  -- where
  --   users.permissions = permissions.permid
  --   and names.nameid = users.userid
  --   and customers.custid = users.userid
  --   and customerstatus.statusid = customers.custactive;