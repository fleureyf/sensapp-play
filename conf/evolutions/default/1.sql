# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table sensor (
  id                        bigint not null,
  name                      varchar(255),
  server_base_url           varchar(255),
  constraint pk_sensor primary key (id))
;

create table server (
  base_url                  varchar(255) not null,
  name                      varchar(255),
  constraint pk_server primary key (base_url))
;

create sequence sensor_seq;

create sequence server_seq;

alter table sensor add constraint fk_sensor_server_1 foreign key (server_base_url) references server (base_url) on delete restrict on update restrict;
create index ix_sensor_server_1 on sensor (server_base_url);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists sensor;

drop table if exists server;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists sensor_seq;

drop sequence if exists server_seq;

