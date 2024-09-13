-- 상품 테이블 생성
CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price INT NOT NULL
);

-- 주문 테이블 생성
CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        product_id BIGINT,
                        quantity INT NOT NULL,
                        order_date TIMESTAMP NOT NULL,
                        FOREIGN KEY (product_id) REFERENCES products(id)
);
