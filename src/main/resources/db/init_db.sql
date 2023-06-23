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

-- Table: "Shop".cities

-- DROP TABLE "Shop".cities;

CREATE TABLE "Shop".cities
(
    id   bigint                                     NOT NULL DEFAULT nextval('"Shop".cities_id_seq'::regclass),
    name character(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cities_pkey PRIMARY KEY (id)
)
    WITH ( OIDS = FALSE)
    TABLESPACE pg_default;

ALTER TABLE "Shop".cities
    OWNER to postgres;

-- Table: "Shop".addresses

-- DROP TABLE "Shop".addresses;

CREATE TABLE "Shop".addresses
(
    id      bigint                                     NOT NULL DEFAULT nextval('"Shop".addresses_id_seq'::regclass),
    street  character(30) COLLATE pg_catalog."default" NOT NULL,
    build   character(10) COLLATE pg_catalog."default" NOT NULL,
    city_id bigint                                     NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (id),
    CONSTRAINT city_fkey FOREIGN KEY (city_id)
        REFERENCES "Shop".cities (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH ( OIDS = FALSE)
    TABLESPACE pg_default;

ALTER TABLE "Shop".addresses
    OWNER to postgres;

-- Table: "Shop".categories

-- DROP TABLE "Shop".categories;

CREATE TABLE "Shop".categories
(
    id   bigint                                     NOT NULL DEFAULT nextval('"Shop".categories_id_seq'::regclass),
    name character(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE "Shop".categories
    OWNER to postgres;

-- Table: "Shop".products

-- DROP TABLE "Shop".products;

CREATE TABLE "Shop".products
(
    id          bigint                                             NOT NULL DEFAULT nextval('"Shop".products_id_seq'::regclass),
    name        character varying(30) COLLATE pg_catalog."default" NOT NULL,
    price       numeric                                            NOT NULL,
    category_id bigint                                             NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT category_fkey FOREIGN KEY (category_id)
        REFERENCES "Shop".categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE "Shop".products
    OWNER to postgres;

-- Table: "Shop".stores

-- DROP TABLE "Shop".stores;

CREATE TABLE "Shop".stores
(
    id         bigint                                             NOT NULL DEFAULT nextval('"Shop".stores_id_seq'::regclass),
    name       character varying(30) COLLATE pg_catalog."default" NOT NULL,
    address_id bigint                                             NOT NULL,
    CONSTRAINT store_pkey PRIMARY KEY (id),
    CONSTRAINT address_fk FOREIGN KEY (address_id)
        REFERENCES "Shop".addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE "Shop".stores
    OWNER to postgres;
