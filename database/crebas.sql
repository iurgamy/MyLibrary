/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20.05.2014 10:47:42                          */
/*==============================================================*/
drop DATABASE if exists LIBRARY;
CREATE DATABASE LIBRARY;
USE LIBRARY;

drop table if exists BOOKS;

drop table if exists CLIENTS;

drop table if exists ORDERS;

drop table if exists ORDER_ITEMS;

drop table if exists SUBSCRIPTIONS;

drop table if exists SUBSCRIPTION_ITEMS;

drop table if exists USERS;

/*==============================================================*/
/* Table: BOOKS                                                 */
/*==============================================================*/
create table BOOKS
(
   ID                   int not null auto_increment,
   TITLE                varchar(255),
   AUTHOR               varchar(255),
   YEAR                 date,
   PRICE                double,
   COUNT                int not null,
   primary key (ID)
);

/*==============================================================*/
/* Table: CLIENTS                                               */
/*==============================================================*/
create table CLIENTS
(
   ID                   int not null auto_increment,
   SUBSCRIPTIONID       int,
   NAME                 varchar(255),
   SURNAME              varchar(255),
   BIRTHDAY             date,
   USERID               int,
   primary key (ID),
   UNIQUE (USERID)
);

/*==============================================================*/
/* Table: ORDERS                                                */
/*==============================================================*/
create table ORDERS
(
   ID                   int not null auto_increment,
   CLIENTID             int not null,
   STATUS               varchar(255),
   primary key (ID)
);

/*==============================================================*/
/* Table: ORDER_ITEMS                                           */
/*==============================================================*/
create table ORDER_ITEMS
(
   ID                   int not null auto_increment,
   ORDERID              int,
   BOOKID               int,
   primary key (ID)
);

/*==============================================================*/
/* Table: SUBSCRIPTIONS                                         */
/*==============================================================*/
create table SUBSCRIPTIONS
(
   ID                   int not null auto_increment,
   CLIENTID             int,
   primary key (ID)
);

/*==============================================================*/
/* Table: SUBSCRIPTION_ITEMS                                    */
/*==============================================================*/
create table SUBSCRIPTION_ITEMS
(
   ID                   int not null auto_increment,
   SUBSCRIPTIONID       int,
   BOOKID               int,
   TYPE                 varchar(255),
   primary key (ID)
);

/*==============================================================*/
/* Table: USERS                                                 */
/*==============================================================*/
create table USERS
(
   id                   int not null auto_increment,
   login                varchar(255) not null,
   password             varchar(255) not null,
   primary key (id),
   UNIQUE (login)
);

alter table CLIENTS add constraint FK_CLIENTS_TO_USERS foreign key (USERID)
      references USERS (id) on delete restrict on update restrict;

alter table ORDERS add constraint FK_ORDERS_TO_CLIENTS foreign key (CLIENTID)
      references CLIENTS (ID) on delete restrict on update restrict;

alter table ORDER_ITEMS add constraint FK_ORDER_ITEMS_TO_BOOKS foreign key (BOOKID)
      references BOOKS (ID) on delete restrict on update restrict;

alter table ORDER_ITEMS add constraint FK_ORDER_ITEMS_TO_ORDERS foreign key (ORDERID)
      references ORDERS (ID) on delete restrict on update restrict;

alter table SUBSCRIPTIONS add constraint FK_SUBSCRIPTIONS_TO_CLIENTS foreign key (CLIENTID)
      references CLIENTS (ID) on delete restrict on update restrict;

alter table SUBSCRIPTION_ITEMS add constraint FK_SUBSCRIPTION_ITEMS_TO_BOOKS foreign key (BOOKID)
      references BOOKS (ID) on delete restrict on update restrict;

alter table SUBSCRIPTION_ITEMS add constraint FK_SUBSCRIPTION_ITEMS_TO_SUBSCRIPTIONS foreign key (SUBSCRIPTIONID)
      references SUBSCRIPTIONS (ID) on delete restrict on update restrict;

