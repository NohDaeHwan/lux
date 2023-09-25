package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.auction.QAuction;
import com.used.lux.domain.constant.AuctionState;
import com.used.lux.domain.constant.GenterType;
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
        JPQLQuery<Auction> queryResult;

        if (auctionDate.equals("")) {
            queryResult = from(auction)
                    .select(auction)
                    .where(auction.aucState.eq(AuctionState.valueOf(auctionState)),
                            auction.prod.prodNm.like("%"+query+"%"));
        } else {
            String[] dateResult = auctionDate.split("-");

            queryResult = from(auction)
                    .select(auction)
                    .where(auction.aucState.eq(AuctionState.valueOf(auctionState)),
                            auction.prod.prodNm.like("%"+query+"%"),
                            auction.aucStartDate.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                    Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));
        }
        

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
                .where(auction.prod.prodColor.like("%"+auctionColor+"%"),
                        auction.prod.prodBrand.brandName.eq("%"+auctionBrand+"%"),
                        auction.prod.prodGender.eq(GenterType.valueOf(auctionGender)),
                        auction.prod.prodSize.like("%"+auctionSize+"%"),
                        auction.prod.prodNm.like("%"+query+"%"),
                        auction.presentPrice.gt(Integer.parseInt(minPrice)),
                        auction.presentPrice.lt(Integer.parseInt(maxPrice)))
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
                .where( auction.prod.cateM.id.eq(mcategoryId),
                        auction.prod.prodColor.like("%"+auctionColor+"%"),
                        auction.prod.prodGender.eq(GenterType.valueOf(auctionGender)),
                        auction.prod.prodSize.like("%"+auctionSize+"%"),
                        auction.prod.prodNm.like("%"+query+"%"),
                        auction.presentPrice.gt(Integer.parseInt(minPrice)),
                        auction.presentPrice.lt(Integer.parseInt(maxPrice)),
                        auction.aucState.eq(AuctionState.SELL)).limit(10)
                .orderBy(auction.createdAt.desc());
        return queryResult.fetch();
    }

}
