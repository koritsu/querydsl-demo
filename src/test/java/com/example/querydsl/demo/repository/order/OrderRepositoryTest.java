package com.example.querydsl.demo.repository.order;

import com.example.querydsl.demo.config.QuerydslConfig;
import com.example.querydsl.demo.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Import(QuerydslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @DisplayName("수량이 0보다 크고 주어진 quantity 보다 작은 주문들을 찾는 쿼리")
    @Test
    void findOrdersNoneZeroQuantityAndGreaterThan() {
        var orders = orderRepository.findOrdersNoneZeroQuantityAndGreaterThan(6);
        System.out.println(
                "result ---->" + (long) orders.size()
        );
        assertNotNull(orders);
        assertEquals(4510, orders.size());
    }

}