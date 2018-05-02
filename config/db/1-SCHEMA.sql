drop schema if exists `computer-database-db`;
  create schema if not exists `computer-database-db`;
  use `computer-database-db`;	

  drop table if exists computer;
  drop table if exists company;
  drop table if exists users;
  drop table if exists roles;
  drop table if exists privileges;
  drop table if exists users_role;
  drop table if exists roles;
  

CREATE TABLE company (
    ca_id BIGINT NOT NULL AUTO_INCREMENT,
    ca_name VARCHAR(255),
    CONSTRAINT pk_company PRIMARY KEY (ca_id)
)
;

CREATE TABLE computer (
    cu_id BIGINT NOT NULL AUTO_INCREMENT,
    cu_name VARCHAR(255) NOT NULL,
    cu_introduced DATETIME NULL,
    cu_discontinued DATETIME NULL,
    cu_ca_id BIGINT DEFAULT NULL,
    CONSTRAINT pk_computer PRIMARY KEY (cu_id)
)
;
  
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);
                 
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
 
CREATE TABLE privileges (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
 
CREATE TABLE users_role (
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

CREATE TABLE role_privileges (
    role_id BIGINT NOT NULL,
    privilege_id BIGINT NOT NULL
);


  alter table computer add constraint fk_computer_company_1 foreign key (cu_ca_id) references company (ca_id) on delete restrict on update restrict;
  create index ix_computer_company_1 on computer (cu_ca_id);
  
