-- INSERT 3_000_000 data

INSERT INTO shop.leftover (store_id, product_id, amount)
SELECT s.id, p.id, floor(random() * (1000 - 5 + 1) + 5)::int
FROM shop.stores s
         CROSS JOIN shop.products p
WHERE NOT EXISTS(
        SELECT 1
        FROM shop.leftover l
        WHERE l.store_id = s.id
          AND l.product_id = p.id
    );
