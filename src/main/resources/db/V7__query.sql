

EXPLAIN (ANALYZE, BUFFERS)
SELECT a.id, a.name AS address, ct.name, l.amount AS max_product_count
FROM shop.addresses a
         JOIN shop.stores s ON a.id = s.address_id
         JOIN shop.leftover l ON s.id = l.store_id
         JOIN shop.products p ON l.product_id = p.id
         JOIN shop.categories c ON p.category_id = c.id
         JOIN shop.cities ct ON a.city_id = ct.id
WHERE c.name = 'Продукти'
  AND l.amount = (
    SELECT MAX(amount)
    FROM shop.leftover l2
             JOIN shop.stores s2 ON l2.store_id = s2.id
             JOIN shop.products p2 ON l2.product_id = p2.id
             JOIN shop.categories c2 ON p2.category_id = c2.id
    WHERE c2.name = 'Продукти'
);
SELECT * FROM shop.leftover l
                  JOIN shop.categories ct ON ct.id=product_id
                  JOIN shop.products p ON l.product_id = p.id
WHERE ct.id=1;

SELECT * FROM shop.leftover WHERE amount = (SELECT MAX(amount) FROM shop.leftover);

EXPLAIN (ANALYZE, BUFFERS)
SELECT a.id, a.name AS address, ct.name, l.amount AS max_product_count
FROM shop.addresses a
         JOIN shop.stores s ON a.id = s.address_id
         JOIN shop.leftover l ON s.id = l.store_id
         JOIN shop.products p ON l.product_id = p.id
         JOIN shop.categories c ON p.category_id = c.id
         JOIN shop.cities ct ON a.city_id = ct.id
WHERE c.name = 'Продукти'
GROUP BY a.id, a.name, ct.name, l.amount
ORDER BY MAX(amount) DESC NULLS LAST
    FETCH FIRST 1 ROW WITH TIES;

EXPLAIN (ANALYZE, BUFFERS)
WITH max_product_count AS (
    SELECT a.id, a.name AS address, l.amount AS max_product_count,
           RANK() OVER (ORDER BY l.amount DESC) AS rank
    FROM shop.addresses a
             JOIN shop.stores s ON a.id = s.address_id
             JOIN shop.leftover l ON s.id = l.store_id
             JOIN shop.products p ON l.product_id = p.id
             JOIN shop.categories c ON p.category_id = c.id
    WHERE c.name = 'Продукти'
)
SELECT id, address, max_product_count
FROM max_product_count
WHERE rank = 1;

EXPLAIN (ANALYZE, BUFFERS)
SELECT a.id, a.name AS address, l.amount AS max_product_count
FROM (
         SELECT store_id, MAX(amount) AS max_amount
         FROM shop.leftover
                  JOIN shop.stores ON leftover.store_id = stores.id
                  JOIN shop.products ON leftover.product_id = products.id
                  JOIN shop.categories ON products.category_id = categories.id
         WHERE shop.categories.name = 'Продукти'
         GROUP BY store_id
     ) AS max_counts
         JOIN shop.leftover l ON max_counts.store_id = l.store_id AND max_counts.max_amount = l.amount
         JOIN shop.stores s ON l.store_id = s.id
         JOIN shop.addresses a ON s.address_id = a.id;


EXPLAIN (ANALYZE, BUFFERS)
SELECT a.id, a.name AS address, ct.name, SUM(l.amount) AS max_product_count
FROM shop.addresses a
         JOIN shop.stores s ON a.id = s.address_id
         JOIN shop.leftover l ON s.id = l.store_id
         JOIN shop.products p ON l.product_id = p.id
         JOIN shop.categories c ON p.category_id = c.id
         JOIN shop.cities ct ON a.city_id = ct.id
WHERE c.name = 'Продукти'
  AND l.amount = (
    SELECT MAX(amount)
    FROM shop.leftover l2
             JOIN shop.stores s2 ON l2.store_id = s2.id
             JOIN shop.products p2 ON l2.product_id = p2.id
             JOIN shop.categories c2 ON p2.category_id = c2.id
    WHERE c2.name = 'Продукти'
)
GROUP BY a.id, a.name, ct.name;
-- ORDER BY max_product_count DESC;

-- RIGHT WAY !!!
EXPLAIN (ANALYZE, BUFFERS)
SELECT ct.name, a.name, SUM(l.amount) AS amount
FROM shop.leftover l
         JOIN shop.stores s ON l.store_id=s.id
         JOIN shop.addresses a ON s.address_id=a.id
         JOIN shop.cities ct ON ct.id=a.city_id
         JOIN shop.products p ON p.id=l.product_id
         JOIN shop.categories c ON c.id=p.category_id
WHERE c.name = 'Продукти'
GROUP BY ct.name, a.name
ORDER BY amount DESC
    FETCH FIRST 1 ROW WITH TIES;
