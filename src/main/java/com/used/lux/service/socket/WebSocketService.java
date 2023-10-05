package com.used.lux.service.socket;

import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.auction.AuctionLog;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.domain.useraccount.UserAccountLog;
import com.used.lux.repository.auction.AuctionLogRepository;
import com.used.lux.repository.auction.AuctionRepository;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.server.ServerEndpoint;
import java.util.*;

@Service
@Slf4j
@ServerEndpoint(value="/auction/{auctionId}/bidder")
public class WebSocketService extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();

    private final AuctionRepository auctionRepository;

    private final AuctionLogRepository auctionLogRepository;

    private final UserAccountRepository userAccountRepository;

    private final UserAccountLogRepository userAccountLogRepository;

    WebSocketService(@Autowired AuctionRepository auctionRepository,
                     @Autowired AuctionLogRepository auctionLogRepository,
                     @Autowired UserAccountRepository userAccountRepository,
                     @Autowired UserAccountLogRepository userAccountLogRepository) {
        this.auctionRepository = auctionRepository;
        this.auctionLogRepository = auctionLogRepository;
        this.userAccountRepository = userAccountRepository;
        this.userAccountLogRepository = userAccountLogRepository;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            String payload = message.getPayload();

            String[] data = payload.split(":");
            for (String d : data) {
                log.info(d);
            }

            for(WebSocketSession sess: list) {
                System.out.println(sess.getPrincipal().getName());
                sess.sendMessage(message);
            }

            Auction auction = auctionRepository.findById(Long.parseLong(data[0])).get();
            System.out.println(auction);

            // 새로 입찰 한 사람
            UserAccount userAccount = userAccountRepository.findByUserName(data[1]);
            userAccount.setUserPoint(userAccount.getUserPoint()-Integer.parseInt(data[2]));
            userAccountRepository.save(userAccount);
            userAccountLogRepository.save(UserAccountLog.builder()
                    .id(null).userId(userAccount.getId()).orderId(auction.getId()).userGrade(userAccount.getUserGrade())
                    .point(Long.parseLong(data[2])).usageDetail("차감").saleNumber("경매/"+data[0])
                    .build());

            if (auction.getUserId() != null) {
                // 전에 입찰 한 사람
                UserAccount beforeUserAccount = userAccountRepository.findById(auction.getUserId()).get();
                beforeUserAccount.setUserPoint(beforeUserAccount.getUserPoint()+auction.getPresentPrice());
                userAccountRepository.save(beforeUserAccount);
                userAccountLogRepository.save(UserAccountLog.builder()
                        .id(null).userId(beforeUserAccount.getId()).orderId(auction.getId()).userGrade(beforeUserAccount.getUserGrade())
                        .point(auction.getPresentPrice()).usageDetail("충전").saleNumber("경매/"+data[0])
                        .build());
            }


            auction.setUserId(userAccount.getId());
            auction.setPresentPrice(Long.parseLong(data[2]));
            auction.setBiddingCnt(auction.getBiddingCnt()+1);
            auctionRepository.save(auction);
            auctionLogRepository.save(AuctionLog.builder()
                            .id(null).userId(userAccount.getId()).aucId(Long.parseLong(data[0]))
                            .prodNm(auction.getProd().getProdNm()).presentPrice(Long.parseLong(data[2]))
                            .build());
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
        log.info(session + " 클라이언트 접속");
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + " 클라이언트 접속 해제");
        list.remove(session);
    }

}