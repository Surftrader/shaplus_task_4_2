CREATE TABLE shop.cities
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name CHARACTER(15)         NOT NULL
);

CREATE TABLE shop.addresses
(
    id      BIGSERIAL PRIMARY KEY NOT NULL,
    name    CHARACTER(30)         NOT NULL,
    city_id BIGINT                NOT NULL,
    CONSTRAINT city_fkey FOREIGN KEY (city_id) REFERENCES shop.cities (id)
);

CREATE TABLE shop.categories
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name CHARACTER(30)         NOT NULL
);

CREATE TABLE shop.stores
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       CHARACTER VARYING(30) NOT NULL,
    address_id BIGINT                NOT NULL,
    CONSTRAINT address_fk FOREIGN KEY (address_id) REFERENCES shop.addresses (id)
);

CREATE TABLE shop.products
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        CHARACTER VARYING(30) NOT NULL,
    price       NUMERIC(15, 2)        NOT NULL,
    category_id BIGINT                NOT NULL,
    CONSTRAINT category_fkey FOREIGN KEY (category_id) REFERENCES shop.categories (id)
);

CREATE TABLE shop.leftover
(
    store_id   BIGINT NOT NULL,
    product_id BIGINT,
    amount     BIGINT,
    CONSTRAINT store_fkey FOREIGN KEY (store_id) REFERENCES shop.stores (id),
    CONSTRAINT product_fkey FOREIGN KEY (product_id) REFERENCES shop.products (id)
);
