package com.example.querydsl.demo.repository.order;

import com.example.querydsl.demo.domain.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findOrdersNoneZeroQuantityAndGreaterThan(final int quantity);
}
