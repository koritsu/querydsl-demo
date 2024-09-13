package com.example.querydsl.demo.repository.product;

import com.example.querydsl.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ProductRepositoryCustom {
    List<Product> findProductsByPriceGreaterThan(int price);

    //TODO: 실습 2. 제공된 ProductRepositoryCustom에서 동적 쿼리를 만들어 봅시다.
    List<Product> findProductsLikeNameAndPrice(final String name, final int minPrice);

    //TODO: 실습 3. 상품을 가격 역순으로 정렬해서 페이징으로 가져와 봅시다.
    Page<Product> findProductsOrderByPriceDesc(final Pageable pageable);

    //TODO: 실습 4. N+1 확인하고 해결하기
    List<Product> findProductsWithStore(final Set<Long> productIds);

    List<Product> findProductsWithStoreFetchJoin(final Set<Long> productIds);
}
