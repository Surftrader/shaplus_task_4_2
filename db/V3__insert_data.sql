-- TABLE CITIES

INSERT INTO shop.cities (name)
VALUES ('Київ');
INSERT INTO shop.cities (name)
VALUES ('Дніпро');
INSERT INTO shop.cities (name)
VALUES ('Винниця');
INSERT INTO shop.cities (name)
VALUES ('Львів');
INSERT INTO shop.cities (name)
VALUES ('Одеса');
INSERT INTO shop.cities (name)
VALUES ('Полтава');
INSERT INTO shop.cities (name)
VALUES ('Харків');

-- TABLE ADDRESSES

INSERT INTO shop.addresses (name, city_id)
VALUES ('пр-т Степана Бандери ,36-А', 1);
INSERT INTO shop.addresses (name, city_id)
VALUES ('пр-т Степана Бандери, 11-А', 1);
INSERT INTO shop.addresses (name, city_id)
VALUES ('Дніпровська наб., 13-В', 1);
INSERT INTO shop.addresses (name, city_id)
VALUES ('вул. Стартова, 9-А', 2);
INSERT INTO shop.addresses (name, city_id)
VALUES ('Запорізьке шосе, 62-К', 2);
INSERT INTO shop.addresses (name, city_id)
VALUES ('вул. Набережна Заводська, 97-А', 2);
INSERT INTO shop.addresses (name, city_id)
VALUES ('вул. Батозька, 1-В', 3);
INSERT INTO shop.addresses (name, city_id)
VALUES ('вул. 600-річчя, 17', 3);
INSERT INTO shop.addresses (name, city_id)
VALUES ('в.Богдана Хмельницького,188-А', 4);
INSERT INTO shop.addresses (name, city_id)
VALUES ('вул. Городоцька, 302', 4);
INSERT INTO shop.addresses (name, city_id)
VALUES ('вул. Щирецька, 7', 4);
INSERT INTO shop.addresses (name, city_id)
VALUES ('пр-т Небесної Сотні, 2', 5);
INSERT INTO shop.addresses (name, city_id)
VALUES ('Київське шосе, 41', 6);
INSERT INTO shop.addresses (name, city_id)
VALUES ('вул. Героїв Праці, 9-А', 7);
INSERT INTO shop.addresses (name, city_id)
VALUES ('пр-т Юрія Гагаріна, 352', 7);
INSERT INTO shop.addresses (name, city_id)
VALUES ('вул. Євгена Котляра, 8/10-А', 7);

-- TABLE CATEGORIES

INSERT INTO shop.categories (name)
VALUES ('Продукти');
INSERT INTO shop.categories (name)
VALUES ('Зоотовари');
INSERT INTO shop.categories (name)
VALUES ('Електроніка');
INSERT INTO shop.categories (name)
VALUES ('Спортивні товари');
INSERT INTO shop.categories (name)
VALUES ('Канцтовари');
INSERT INTO shop.categories (name)
VALUES ('Побутова техніка');
INSERT INTO shop.categories (name)
VALUES ('Інструменти');
INSERT INTO shop.categories (name)
VALUES ('Краса та здоров`я');
INSERT INTO shop.categories (name)
VALUES ('Одяг, Взуття, Аксесуари');
INSERT INTO shop.categories (name)
VALUES ('Аптека');

-- TABLE STORES

INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-1', 1);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-2', 2);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-3', 3);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-4', 4);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-5', 5);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-6', 6);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-7', 7);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-8', 8);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-9', 9);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-10', 10);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-11', 11);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-12', 12);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-13', 13);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-14', 14);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-15', 15);
INSERT INTO shop.stores (name, address_id)
VALUES ('ТЦ ЕПІЦЕНТР-16', 16);
