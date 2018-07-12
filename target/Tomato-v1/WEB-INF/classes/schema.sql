drop table if exists users;
drop table if exists hotels;
drop table if exists items;
drop table if exists reviews;
drop table if exists orders;


create table users (uname varchar(255),password varchar(255),primary key (uname));
create table hotels (hname varchar(255),location varchar(255),primary key (hname));
create table reviews (id integer AUTO_INCREMENT,hname varchar(255),rating integer,review varchar(255),uname varchar(255),primary key (id));
create table items (id integer , hname varchar(255),item varchar(255),price integer ,primary key (id));
create table orders (id integer AUTO_INCREMENT,hname varchar(255),item varchar(255),orderid integer,price integer,quantity integer,total integer,uname varchar(255),primary key (id));

