package com.example.querydsl.demo.repository.product;

import com.example.querydsl.demo.domain.Product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.querydsl.demo.domain.QOrder.order;
import static com.example.querydsl.demo.domain.QProduct.product;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Product> findProductsByPriceGreaterThan(final int price) {
        return queryFactory
                .selectFrom(product)
                .where(product.price.gt(price))
                .fetch();
    }

    public List<Product> findProductsLikeNameAndPrice(final String name, final int minPrice) {
        return queryFactory
                .selectFrom(product)
                .where(
                        nameContains(name),
                        priceGt(minPrice)
                )
                .fetch();
    }

    private BooleanExpression nameContains(final String name) {
//        return name != null ? product.name.contains(name) : null;
        return name != null ? product.name.endsWith(name) : null;
    }

    private BooleanExpression priceGt(final int price) {
        return price > 0 ? product.price.gt(price) : null;
    }

    public Page<Product> findProductsOrderByPriceDesc(final Pageable pageable) {
        List<Product> products = queryFactory
                .selectFrom(product)
                .orderBy(product.price.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount =
                Optional.ofNullable(
                        queryFactory.select(product.id.count()).from(product).fetchOne()
                ).orElse(0L);

        return new PageImpl<>(products, pageable, totalCount);
    }

    public List<Product> findProductsWithStore(final Set<Long> productIds) {

        return queryFactory
                .selectFrom(product)
                .where(product.id.in(productIds))
                .fetch()
                ;


    }

    public List<Product> findProductsWithStoreJoinFetch(final Set<Long> productIds) {

        return queryFactory
                .selectFrom(product)
                .join(product.orders, order)
                .where(product.id.in(productIds))
                .fetchJoin()
                .fetch()
                ;

    }

}
