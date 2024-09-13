-- 상품 데이터 1만 건 랜덤 삽입
INSERT INTO products (name, price)
SELECT 'Product_' || x, CAST(RAND() * 100 AS INT)
FROM system_range(1, 10000);

-- 주문 데이터 1만 건 랜덤 삽입
INSERT INTO orders (product_id, quantity, order_date)
SELECT CAST((RAND() * 9999 + 1) AS BIGINT), CAST(RAND() * 10 + 1 AS INT), CURRENT_TIMESTAMP - (RAND() * 365)
FROM system_range(1, 10000);
