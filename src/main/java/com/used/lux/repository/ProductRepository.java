package com.used.lux.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.used.lux.domain.Product;
import com.used.lux.domain.QProduct;
import com.used.lux.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ProductRepository extends
        JpaRepository<Product, Long>,
        ProductRepositoryCustom,
        QuerydslPredicateExecutor<Product>,
        QuerydslBinderCustomizer<QProduct> {

    @Override
    default void customize(QuerydslBindings bindings, QProduct root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.appraisal.appraisalBrand.brandName, root.categoryB.categoryBName,
                root.categoryM.categoryMName, root.appraisal.appraisalGender, root.appraisal.appraisalGrade,
                root.appraisal.appraisalSize, root.appraisal.appraisalProductName,
                root.createdAt, root.createdBy);
        bindings.bind(root.appraisal.appraisalBrand.brandName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.categoryB.categoryBName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.categoryM.categoryMName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.appraisal.appraisalGender).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.appraisal.appraisalGrade).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.appraisal.appraisalSize).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.appraisal.appraisalProductName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

}
