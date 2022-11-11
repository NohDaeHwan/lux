package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.Auction;
import com.used.lux.domain.QAuction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class AuctionRepositoryCustomImpl extends QuerydslRepositorySupport implements AuctionRepositoryCustom {

    public AuctionRepositoryCustomImpl() {
        super(Auction.class);
    }

    @Override
    public Page<Auction> searchAuction(String auctionState, String auctionDate, String query, Pageable pageable) {
        QAuction auction = QAuction.auction;

        String[] dateResult = auctionDate.split("-");

        JPQLQuery<Auction> queryResult = from(auction)
                .select(auction)
                .where(auction.state.stateStep.like("%"+auctionState+"%"),
                        auction.product.appraisalRequest.appraisalProductName.like("%"+query+"%"),
                        auction.auctionStartDate.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));
        long totalCount = queryResult.fetchCount();
        List<Auction> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Auction>(results, pageable, totalCount);
    }

    @Override
    public Page<Auction> findByQuery(String auctionColor, String auctionBrand, String auctionGender, String auctionSize,
                                     String auctionGrade, String maxPrice, String minPrice, String query, Pageable pageable) {
        QAuction auction = QAuction.auction;

        JPQLQuery<Auction> queryResult = from(auction)
                .select(auction)
                .where(auction.product.appraisalRequest.appraisalColor.like("%"+auctionColor+"%"),
                        auction.product.appraisalRequest.appraisalBrand.brandName.eq("%"+auctionBrand+"%"),
                        auction.product.appraisalRequest.appraisalGender.like("%"+auctionGender+"%"),
                        auction.product.appraisalRequest.appraisalSize.like("%"+auctionSize+"%"),
                        auction.product.appraisalRequest.appraisalProductName.like("%"+query+"%"),
                        auction.product.productPrice.gt(Integer.parseInt(minPrice)),
                        auction.product.productPrice.lt(Integer.parseInt(maxPrice)))
                .orderBy(auction.createdAt.desc());
        long totalCount = queryResult.fetchCount();
        List<Auction> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Auction>(results, pageable, totalCount);
    }

    @Override
    public List<Auction> findByCategoryQuery(Long mcategoryId, String auctionColor, String auctionBrand, String auctionGender, String auctionSize, String auctionGrade, String maxPrice, String minPrice, String query, Pageable pageable) {
        QAuction auction = QAuction.auction;

        JPQLQuery<Auction> queryResult = from(auction)
                .select(auction)
                .where(auction.product.appraisalRequest.appraisalColor.like("%"+auctionColor+"%"),
                        auction.product.appraisalRequest.appraisalBrand.brandName.eq("%"+auctionBrand+"%"),
                        auction.product.appraisalRequest.appraisalGender.like("%"+auctionGender+"%"),
                        auction.product.appraisalRequest.appraisalSize.like("%"+auctionSize+"%"),
                        auction.product.appraisalRequest.appraisalProductName.like("%"+query+"%"),
                        auction.product.productPrice.gt(Integer.parseInt(minPrice)),
                        auction.product.productPrice.lt(Integer.parseInt(maxPrice))).limit(10)
                .orderBy(auction.createdAt.desc());
        return queryResult.fetch();
    }

}
