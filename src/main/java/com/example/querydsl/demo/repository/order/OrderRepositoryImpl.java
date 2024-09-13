package com.example.querydsl.demo.repository.order;

import com.example.querydsl.demo.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.querydsl.demo.domain.QOrder.order;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    @Override
    public List<Order> findOrdersNoneZeroQuantityAndGreaterThan(int quantity) {

        return queryFactory.selectFrom(order)
                .where(
//                        order.quantity.between(0, quantity)
                        order.quantity.gt(0)
                                .and(
                                        order.quantity.lt(quantity))
                )
                .fetch();
    }
}
