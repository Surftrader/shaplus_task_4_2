-- EXPLAIN (ANALYZE, BUFFERS)
SELECT s.id, ct.name, a.name, SUM(l.amount) AS amount
FROM shop.leftover l
         JOIN shop.stores s ON l.store_id = s.id
         JOIN shop.addresses a ON s.address_id = a.id
         JOIN shop.cities ct ON ct.id = a.city_id
         JOIN shop.products p ON p.id = l.product_id
         JOIN shop.categories c ON c.id = p.category_id
WHERE c.name = 'Продукти'
GROUP BY s.id, ct.name, a.name
ORDER BY amount DESC
    FETCH FIRST 1 ROW
WITH TIES;
