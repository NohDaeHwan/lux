package com.used.lux.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.used.lux.domain.QUserAccount;
import com.used.lux.domain.UserAccount;
import com.used.lux.repository.querydsl.UserAccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Optional;

public interface UserAccountRepository extends
        JpaRepository<UserAccount, Long>,
        UserAccountRepositoryCustom,
        QuerydslPredicateExecutor<UserAccount>,
        QuerydslBinderCustomizer<QUserAccount> {
    Optional<UserAccount> findByUserEmail(String username);

    @Override
    default void customize(QuerydslBindings bindings, QUserAccount root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.userEmail, root.userName, root.userGrade.gradeName,
                root.phoneNumber, root.age, root.gender, root.createdAt, root.createdBy);
        bindings.bind(root.userEmail).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.userName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.userGrade.gradeName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.phoneNumber).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.age).first(NumberExpression::eq);
        bindings.bind(root.gender).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
