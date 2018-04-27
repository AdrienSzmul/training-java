drop schema if exists `computer-database-db`;
  create schema if not exists `computer-database-db`;
  use `computer-database-db`;	

  drop table if exists computer;
  drop table if exists company;

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
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username)
        REFERENCES users (username)
);


  alter table computer add constraint fk_computer_company_1 foreign key (cu_ca_id) references company (ca_id) on delete restrict on update restrict;
  create index ix_computer_company_1 on computer (cu_ca_id);
  create unique index ix_auth_username on authorities (username,authority);
