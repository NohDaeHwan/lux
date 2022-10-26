package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.Product;
import com.used.lux.domain.QUserAccount;
import com.used.lux.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class UserAccountRepositoryCustomImpl extends QuerydslRepositorySupport implements UserAccountRepositoryCustom {

    public UserAccountRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public Page<UserAccount> searchUser(String gender, String age, String grade, String date,
                                        String query, Pageable pageable) {
        QUserAccount userAccount = QUserAccount.userAccount;

        String[] dateResult = date.split("-");

        JPQLQuery<UserAccount> queryResult = from(userAccount)
                .select(userAccount)
                .where(userAccount.gender.like("%"+gender+"%"),
                        userAccount.age.like("%"+age+"%"),
                        userAccount.userGrade.gradeName.like("%"+grade+"%"),
                        userAccount.userEmail.like("%"+query+"%"),
                        userAccount.createdAt.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));
        long totalCount = queryResult.fetchCount();
        List<UserAccount> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<UserAccount>(results, pageable, totalCount);
    }

}
