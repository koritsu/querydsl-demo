package com.example.querydsl.demo.repository.product;

import com.example.querydsl.demo.config.QuerydslConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(QuerydslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    UnaryOperator<Object> logPrefixAdder = (s -> "result --->" + s);

    @DisplayName("10개 이상 상품 조회")
    @Test
    void findProductsByPriceGreaterThan() {
        var products = productRepository.findProductsByPriceGreaterThan(90);


        System.out.println(
                logPrefixAdder.apply(products.size())
        );

        assertNotNull(products);
    }

    @DisplayName("상품명과 가격으로 동적쿼리")
    @Test
    void findProductsByNameAndPrice() {
        var products = productRepository.findProductsLikeNameAndPrice("100", 0);

        System.out.println(
                logPrefixAdder.apply(products.size())
        );

        assertNotNull(products);
        assertEquals(10, products.size());

        var products2 = productRepository.findProductsLikeNameAndPrice(null, 50);

        System.out.println(
                logPrefixAdder.apply(products2.size())
        );

        assertNotNull(products2);
        assertEquals(4942, products2.size());

        var products3 = productRepository.findProductsLikeNameAndPrice("100", 50);

        System.out.println(
                logPrefixAdder.apply(products3.size())
        );

        assertNotNull(products3);
        assertEquals(3, products3.size());

    }

    @DisplayName("페이징 쿼리")
    @Test
    void findProductsOrderByPriceDesc() {
        var products = productRepository.findProductsOrderByPriceDesc(Pageable.ofSize(10));

        System.out.println(
                logPrefixAdder.apply(products.getTotalElements())
        );
        System.out.println(
                logPrefixAdder.apply(products.getContent().size())
        );

        assertNotNull(products);
        assertEquals(10000, products.getTotalElements());
        assertEquals(10, products.getContent().size());
    }

    @DisplayName("여러 아이디로 상품과 가게 정보 조회")
    @Test
    void findProductsWithStore() {
        var products = productRepository.findProductsWithStore(Set.of(1L, 2L, 3L, 4L));
        var orders = products.stream().flatMap(p -> p.getOrders().stream()).toList();

        assertNotNull(products);
        assertNotNull(orders);
    }

    @DisplayName("여러 아이디로 상품과 가게 정보 조회 - fetch join")
    @Test
    void findProductsWithStoreJoinFetch() {
        var products = productRepository.findProductsWithStoreJoinFetch(Set.of(1L, 2L, 3L, 4L));
        var orders = products.stream().flatMap(p -> p.getOrders().stream()).toList();

        assertNotNull(products);
        assertNotNull(orders);
    }

}