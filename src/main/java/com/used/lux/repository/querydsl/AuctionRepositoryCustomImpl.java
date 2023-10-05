package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.auction.QAuction;
import com.used.lux.domain.constant.AppraisalGrade;
import com.used.lux.domain.constant.AuctionState;
import com.used.lux.domain.constant.GenterType;
import com.used.lux.domain.constant.SellType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    public List<Auction> findByCateQuery(Long mcategoryId, String auctionColor, String auctionBrand, String auctionGender, String auctionSize, String auctionGrade,
                                         long maxPrice, long minPrice, String query) {
        QAuction auction = QAuction.auction;

        JPQLQuery<Auction> queryResult = from(auction)
                .select(auction)
                .where( auction.prod.cateM.id.eq(mcategoryId),
                        auction.prod.prodColor.like("%"+auctionColor+"%"),
                        auction.prod.prodBrand.brandName.like("%"+auctionBrand+"%"),
                        auction.prod.prodSize.like("%"+auctionSize+"%"),
                        auction.prod.prodNm.like("%"+query+"%"),
                        auction.presentPrice.gt(minPrice),
                        auction.presentPrice.lt(maxPrice),
                        auction.aucState.eq(AuctionState.SELL),
                        auction.prod.prodSellType.eq(SellType.AUCTION)).limit(10)
                .orderBy(auction.createdAt.desc());

        if (!Objects.equals(auctionGender, "")) {
            queryResult.where(auction.prod.prodGender.eq(GenterType.valueOf(auctionGender)));
        }

        if (!Objects.equals(auctionGrade, "")) {
            queryResult.where(auction.prod.prodGrade.eq(AppraisalGrade.valueOf(auctionGrade)));
        }

        return queryResult.fetch();
    }

    @Override
    public Page<Auction> findByBackAucList(String auctionState, String auctionDate, String query, Pageable pageable) {
        QAuction auction = QAuction.auction;

        JPQLQuery<Auction> queryResult = from(auction)
                .select(auction)
                .where(auction.prod.prodNm.like("%"+query+"%"))
                .orderBy(auction.createdAt.desc());

        if (!Objects.equals(auctionState, "")) {
            queryResult.where(auction.aucState.eq(AuctionState.valueOf(auctionState)));
        }

        if (!Objects.equals(auctionDate, "")) {
            String[] dateResult = auctionDate.split("-");
            LocalDateTime date = LocalDateTime.of(Integer.parseInt(dateResult[0]),
                    Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00);
            queryResult.where(auction.aucStartDate.gt(date));
        }

        long totalCount = queryResult.fetchCount();
        List<Auction> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Auction>(results, pageable, totalCount);
    }

}
