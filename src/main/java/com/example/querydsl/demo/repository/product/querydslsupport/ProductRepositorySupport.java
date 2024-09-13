package com.example.querydsl.demo.repository.product.querydslsupport;

import com.example.querydsl.demo.domain.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.querydsl.demo.domain.QProduct.product;

@Repository
public class ProductRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public ProductRepositorySupport(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    public List<Product> findProductsByPriceGreaterThan(int price) {
        return queryFactory
                .selectFrom(product)
                .where(product.price.gt(price))
                .fetch();
    }

}
