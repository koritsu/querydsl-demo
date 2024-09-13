package com.example.querydsl.demo.repository.product;

import com.example.querydsl.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ProductRepositoryCustom {
    List<Product> findProductsByPriceGreaterThan(int price);

    List<Product> findProductsLikeNameAndPrice(final String name, final int minPrice);

    Page<Product> findProductsOrderByPriceDesc(final Pageable pageable);

    List<Product> findProductsWithStore(final Set<Long> productIds);

    List<Product> findProductsWithStoreJoinFetch(final Set<Long> productIds);
}
