package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.Product;
import com.used.lux.domain.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

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
                .where(product.productSellType.like("%"+productSellType+"%"),
                        product.appraisal.appraisalRequest.appraisalBrand.brandName.like("%"+productBrand+"%"),
                        product.appraisal.appraisalRequest.appraisalGender.like("%"+productGender+"%"),
                        product.appraisal.appraisalRequest.appraisalSize.like("%"+productSize+"%"),
                        product.state.stateStep.like("%"+productState+"%"),
                        product.appraisal.appraisalRequest.appraisalProductName.like("%"+query+"%"),
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
                .where(product.appraisal.appraisalRequest.appraisalColor.like("%"+productColor+"%"),
//                        product.appraisal.appraisalRequest.appraisalBrand.brandName.like("%"+productBrand+"%"),
                        product.appraisal.appraisalRequest.appraisalGender.like("%"+productGender+"%"),
                        product.appraisal.appraisalRequest.appraisalSize.like("%"+productSize+"%"),
                        product.appraisal.appraisalRequest.appraisalProductName.like("%"+query+"%"),
                        product.productPrice.gt(Integer.parseInt(minPrice)),
                        product.productPrice.lt(Integer.parseInt(maxPrice)),
                        product.productSellType.eq("중고"),
                        product.state.stateStep.eq("판매중"))
                .orderBy(product.createdAt.desc());
        long totalCount = queryResult.fetchCount();
        List<Product> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Product>(results, pageable, totalCount);
    }


    // 카테고리 검색 중고
    @Override
    public List<Product> findByCategoryQuery(Long mcategoryId, String productColor, String productBrand, String productGender, String productSize,
                                             String productGrade, String maxPrice, String minPrice, String query, Pageable pageable) {
        QProduct product = QProduct.product;

        JPQLQuery<Product> queryResult = from(product)
                .select(product)
                .where(product.appraisal.appraisalRequest.appraisalColor.like("%"+productColor+"%"),
                        product.appraisal.appraisalRequest.appraisalBrand.brandName.like("%"+productBrand+"%"),
                        product.appraisal.appraisalRequest.appraisalGender.like("%"+productGender+"%"),
                        product.appraisal.appraisalRequest.appraisalSize.like("%"+productSize+"%"),
                        product.appraisal.appraisalRequest.appraisalProductName.like("%"+query+"%"),
                        product.categoryM.id.eq(mcategoryId),
                        product.productPrice.gt(Integer.parseInt(minPrice)),
                        product.productPrice.lt(Integer.parseInt(maxPrice)),
                        product.productSellType.eq("중고"),
                        product.state.stateStep.eq("판매중")).limit(10)
                .orderBy(product.createdAt.desc());
        return queryResult.fetch();
    }

}
