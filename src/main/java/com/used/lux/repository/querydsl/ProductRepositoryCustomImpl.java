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
                        product.appraisal.appraisalBrand.brandName.like("%"+productBrand+"%"),
                        product.appraisal.appraisalGender.like("%"+productGender+"%"),
                        product.appraisal.appraisalSize.like("%"+productSize+"%"),
                        product.appraisal.appraisalGrade.like("%"+productGrade+"%"),
                        product.state.stateStep.like("%"+productState+"%"),
                        product.appraisal.appraisalProductName.like("%"+query+"%"),
                        product.createdAt.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));
        long totalCount = queryResult.fetchCount();
        List<Product> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Product>(results, pageable, totalCount);
    }

    @Override
    public Page<Product> findByQuery(String productColor, String productBrand, String productGender, String productSize,
                                     String productGrade, String productPrice, String productName, Pageable pageable) {
        QProduct product = QProduct.product;

        JPQLQuery<Product> query = from(product)
                .select(product)
                .where(product.appraisal.appraisalColor.like("%"+productColor+"%"),
                        product.appraisal.appraisalBrand.brandName.like("%"+productBrand+"%"),
                        product.appraisal.appraisalGender.like("%"+productGender+"%"),
                        product.appraisal.appraisalSize.like("%"+productSize+"%"),
                        product.appraisal.appraisalGrade.like("%"+productGrade+"%"),
                        product.productPrice.like("%"+productPrice+"%"),
                        product.productSellType.eq("중고"),
                        product.appraisal.appraisalProductName.like("%"+productName+"%"))
                .orderBy(product.createdAt.desc());
        long totalCount = query.fetchCount();
        List<Product> results = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<Product>(results, pageable, totalCount);
    }

}
