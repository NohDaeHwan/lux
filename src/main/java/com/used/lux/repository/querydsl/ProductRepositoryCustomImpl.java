package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.Product;
import com.used.lux.domain.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> findByQuery(String brandName, String categoryBName, String categoryMName, String gender,
                                     String state, String size, String productName, Pageable pageable) {
        QProduct product = QProduct.product;

        JPQLQuery<Product> query = from(product)
                .select(product)
                .where(product.appraisal.appraisalBrand.brandName.like("%"+brandName+"%"),
                        product.categoryB.categoryBName.like("%"+categoryBName+"%"),
                        product.categoryM.categoryMName.like("%"+categoryMName+"%"),
                        product.appraisal.appraisalGender.like("%"+gender+"%"),
                        product.appraisal.appraisalGrade.like("%"+state+"%"),
                        product.appraisal.appraisalSize.like("%"+size+"%"),
                        product.appraisal.appraisalProductName.like("%"+productName+"%"));
        long totalCount = query.fetchCount();
        List<Product> results = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<Product>(results, pageable, totalCount);
    }

}
