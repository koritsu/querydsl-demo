package com.example.querydsl.demo.repository.product.nointerface;

import com.example.querydsl.demo.domain.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.querydsl.demo.domain.QProduct.product;

@RequiredArgsConstructor
@Repository
public class ProductNoInterfaceRepository {

    private final JPAQueryFactory queryFactory;

    public List<Product> findProductsByPriceGreaterThan(int price) {
        return queryFactory
                .selectFrom(product)
                .where(product.price.gt(price))
                .fetch();
    }
}
