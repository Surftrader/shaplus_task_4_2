CREATE
    DATABASE shapp_db
    WITH OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

\c shapp_db;

CREATE TABLE "Shop".cities
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name CHARACTER(15)         NOT NULL
);

CREATE TABLE "Shop".addresses
(
    id      BIGSERIAL PRIMARY KEY NOT NULL,
    street  CHARACTER(30)         NOT NULL,
    build   CHARACTER(10)         NOT NULL,
    city_id BIGINT                NOT NULL,
    CONSTRAINT city_fkey FOREIGN KEY (city_id) REFERENCES "Shop".cities (id)
);

CREATE TABLE "Shop".categories
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name CHARACTER(30)         NOT NULL
);

CREATE TABLE "Shop".products
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        CHARACTER VARYING(30) NOT NULL,
    price       NUMERIC(15, 2)        NOT NULL,
    category_id BIGINT                NOT NULL,
    CONSTRAINT category_fkey FOREIGN KEY (category_id) REFERENCES "Shop".categories (id)
);

CREATE TABLE "Shop".stores
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       CHARACTER VARYING(30) NOT NULL,
    address_id BIGINT                NOT NULL,
    CONSTRAINT address_fk FOREIGN KEY (address_id) REFERENCES "Shop".addresses (id)
);
