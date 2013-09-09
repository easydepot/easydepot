# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table record (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  date_of_birth             timestamp,
  email                     varchar(255),
  constraint pk_record primary key (id))
;

create sequence record_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists record;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists record_seq;

