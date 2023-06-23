-- REVOKE CONNECT ON DATABASE shapp_db FROM PUBLIC;
-- SELECT pg_terminate_backend(pg_stat_activity.pid)
-- FROM pg_stat_activity
-- WHERE pg_stat_activity.datname = 'shapp_db';
DROP DATABASE IF EXISTS shapp_db;

-- CREATE DATABASE "shapp_db"
--     WITH OWNER =postres
--     ENCODING = 'UTF-8'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;

CREATE DATABASE shapp_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE SCHEMA "Shop"
    AUTHORIZATION postgres;

-- CREATE TABLE public."cities"
-- (
--     "id"   SERIAL PRIMARY KEY NOT NULL,
--     "name" CHARACTER(15)      NOT NULL
-- --    CONSTRAINT "cities_pkey"
-- );

CREATE TABLE "Shop".cities
(
    id bigserial NOT NULL,
    name character(15) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public."addresses"
(
    "id"      SERIAL PRIMARY KEY            NOT NULL,
    "street"  CHARACTER(30)                 NOT NULL,
    "build"   CHARACTER(10)                 NOT NULL,
    "city_id" BIGINT REFERENCES cities (id) NOT NULL
--     CONSTRAINT "addresses_pkey"
);

CREATE TABLE public."categories"
(
    "id"   SERIAL PRIMARY KEY NOT NULL,
    "name" CHARACTER(30)      NOT NULL
--     CONSTRAINT "category_pkey"
);

CREATE TABLE public."products"
(
    "id"          SERIAL PRIMARY KEY                NOT NULL,
    "name"        CHARACTER(30)                     NOT NULL,
    "price"       NUMERIC                           NOT NULL,
    "category_id" BIGINT REFERENCES categories (id) NOT NULL
--     CONSTRAINT "products_pkey"
);


CREATE TABLE public."stores"
(
    "id"         SERIAL                           NOT NULL,
    "name"       CHARACTER(30)                    NOT NULL,
    "address_id" BIGINT REFERENCES addresses (id) NOT NULL,
--     CONSTRAINT "stores_pkey"
    PRIMARY KEY ("id")
);
