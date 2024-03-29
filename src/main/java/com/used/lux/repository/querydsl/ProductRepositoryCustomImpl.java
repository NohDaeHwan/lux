package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.constant.AppraisalGrade;
import com.used.lux.domain.constant.GenterType;
import com.used.lux.domain.constant.ProductState;
import com.used.lux.domain.constant.SellType;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.product.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> searchProduct(String productSellType, String productBrand, String productGender,
                                       String productSize, String productGrade, String productState,
                                       String productDate, String query, Pageable pageable) {
        QProduct product = QProduct.product;

        String[] dateResult = productDate.split("-");

        JPQLQuery<Product> queryResult = from(product)
                .select(product)
                .where(product.prodBrand.brandName.like("%"+productBrand+"%"),
                        product.prodGender.eq(GenterType.valueOf(productGender)),
                        product.prodSize.like("%"+productSize+"%"),
                        product.prodState.eq(ProductState.valueOf(productState)),
                        product.prodNm.like("%"+query+"%"),
                        product.createdAt.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));
        long totalCount = queryResult.fetchCount();
        List<Product> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Product>(results, pageable, totalCount);
    }

    @Override
    public Page<Product> findByQuery(String productColor, String productBrand, String productGender, String productSize,
                                     String productGrade, String maxPrice, String minPrice, String query, Pageable pageable) {
        QProduct product = QProduct.product;

        JPQLQuery<Product> queryResult = from(product)
                .select(product)
                .where(product.prodColor.like("%"+productColor+"%"),
                        product.prodBrand.brandName.like("%"+productBrand+"%"),
                        product.prodGender.eq(GenterType.valueOf(productGender)),
                        product.prodSize.like("%"+productSize+"%"),
                        product.prodNm.like("%"+query+"%"),
                        product.prodPrice.gt(Integer.parseInt(minPrice)),
                        product.prodPrice.lt(Integer.parseInt(maxPrice)),
                        product.prodState.eq(ProductState.SELL))
                .orderBy(product.createdAt.desc());
        long totalCount = queryResult.fetchCount();
        List<Product> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Product>(results, pageable, totalCount);
    }


    // 카테고리 검색 중고
    @Override
    public List<Product> findByCateQuery(Long mcategoryId, String productColor, String productBrand, String productGender, String productSize,
                                             String productGrade, long maxPrice, long minPrice, String query) {
        QProduct product = QProduct.product;

        JPQLQuery<Product> queryResult = from(product)
                .select(product)
                .where(product.prodColor.like("%"+productColor+"%"),
                        product.prodBrand.brandName.like("%"+productBrand+"%"),
                        product.prodSize.like("%"+productSize+"%"),
                        product.prodNm.like("%"+query+"%"),
                        product.cateM.id.eq(mcategoryId),
                        product.prodPrice.gt(minPrice),
                        product.prodPrice.lt(maxPrice),
                        product.prodState.eq(ProductState.SELL),
                        product.prodSellType.eq(SellType.USED)).limit(10)
                .orderBy(product.createdAt.desc());

        if (!Objects.equals(productGender, "")) {
            queryResult.where(product.prodGender.eq(GenterType.valueOf(productGender)));
        }

        if (!Objects.equals(productGrade, "")) {
            queryResult.where(product.prodGrade.eq(AppraisalGrade.valueOf(productGrade)));
        }

        return queryResult.fetch();
    }

    @Override
    public Page<Product> findByBackProductList(String productBrand, String productGender, String productSize, String productGrade,
                                               String productState, LocalDateTime productDate, String query, Pageable pageable) {
        QProduct product = QProduct.product;

        JPQLQuery<Product> queryResult = from(product)
                .select(product)
                .where(product.prodBrand.brandName.like("%"+productBrand+"%"),
                        product.prodSize.like("%"+productSize+"%"),
                        product.createdAt.after(productDate),
                        product.prodNm.like("%"+query+"%"));
        if (!Objects.equals(productGender, "")) {
            queryResult.where(product.prodGender.eq(GenterType.valueOf(productGender)));
        }
        if (!Objects.equals(productGrade, "")) {
            queryResult.where(product.prodGrade.eq(AppraisalGrade.valueOf(productGrade)));
        }
        if (!Objects.equals(productState, "")) {
            queryResult.where(product.prodState.eq(ProductState.valueOf(productState)));
        }

        long totalCount = queryResult.fetchCount();
        List<Product> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Product>(results, pageable, totalCount);
    }

    @Override
    public Page<Product> findByFrontProductList(String productBrand, String productColor, String productGender, String productSize,
                                                String productGrade, int maxPrice, int minPrice, String query, Pageable pageable) {
        QProduct product = QProduct.product;

        JPQLQuery<Product> queryResult = from(product)
                .select(product)
                .where(product.prodBrand.id.like("%"+productBrand+"%"),
                        product.prodColor.like("%"+productColor+"%"),
                        product.prodSize.like("%"+productSize+"%"),
                        product.prodPrice.gt(minPrice),
                        product.prodPrice.lt(maxPrice),
                        product.prodNm.like("%"+query+"%"));

        if (!Objects.equals(productGender, "")) {
            queryResult.where(product.prodGender.eq(GenterType.valueOf(productGender)));
        }
        if (!Objects.equals(productGrade, "")) {
            queryResult.where(product.prodGrade.eq(AppraisalGrade.valueOf(productGrade)));
        }

        long totalCount = queryResult.fetchCount();
        List<Product> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Product>(results, pageable, totalCount);
    }

}
