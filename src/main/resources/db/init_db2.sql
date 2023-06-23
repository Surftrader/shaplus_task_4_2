-- Database: shapp_db

-- DROP DATABASE shapp_db;

CREATE
    DATABASE shapp_db
    WITH OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: Shop

-- DROP SCHEMA "Shop" ;

CREATE SCHEMA "Shop"
    AUTHORIZATION postgres;


CREATE TABLE "Shop".cities
(
    id   bigint        NOT NULL,
    name character(15) NOT NULL,
    CONSTRAINT cities_pkey PRIMARY KEY (id)
);

ALTER TABLE "Shop".cities
    OWNER to postgres;

CREATE TABLE "Shop".addresses
(
    id      bigint        NOT NULL,
    street  character(30) NOT NULL,
    build   character(10) NOT NULL,
    city_id bigint        NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (id),
    CONSTRAINT city_fkey FOREIGN KEY (city_id)
        REFERENCES "Shop".cities (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE "Shop".addresses
    OWNER to postgres;

CREATE TABLE "Shop".categories
(
    id   bigint        NOT NULL,
    name character(30) NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (id)
);

ALTER TABLE "Shop".categories
    OWNER to postgres;

CREATE TABLE "Shop".products
(
    id          bigint                NOT NULL,
    name        character varying(30) NOT NULL,
    price       numeric               NOT NULL,
    category_id bigint                NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT category_fkey FOREIGN KEY (category_id)
        REFERENCES "Shop".categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE "Shop".products
    OWNER to postgres;

CREATE TABLE "Shop".stores
(
    id         bigint                NOT NULL,
    name       character varying(30) NOT NULL,
    address_id bigint                NOT NULL,
    CONSTRAINT store_pkey PRIMARY KEY (id),
    CONSTRAINT address_fk FOREIGN KEY (address_id)
        REFERENCES "Shop".addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE "Shop".stores
    OWNER to postgres;
