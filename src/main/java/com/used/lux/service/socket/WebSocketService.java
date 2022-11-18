package com.used.lux.service.socket;

import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.auction.AuctionLog;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.domain.useraccount.UserAccountLog;
import com.used.lux.repository.auction.AuctionLogRepository;
import com.used.lux.repository.auction.AuctionRepository;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
            UserAccount userAccount;

            if (auction.getBidder() == null) {
                // 새로 입찰 한 사람
                userAccount = userAccountRepository.findByUserName(data[1]);
                userAccount.setPoint(userAccount.getPoint()-Integer.parseInt(data[2]));
                userAccountRepository.save(userAccount);
                userAccountLogRepository.save(UserAccountLog.of(null, userAccount.getUserEmail(), userAccount.getUserGrade(),
                        Integer.parseInt(data[2]), "차감", "경매/"+data[0]));
            } else {
                // 새로 입찰 한 사람
                userAccount = userAccountRepository.findByUserName(data[1]);
                userAccount.setPoint(userAccount.getPoint()-Integer.parseInt(data[2]));
                userAccountRepository.save(userAccount);
                userAccountLogRepository.save(UserAccountLog.of(null, userAccount.getUserEmail(), userAccount.getUserGrade(),
                        Integer.parseInt(data[2]), "차감", "경매/"+data[0]));

                // 전에 입찰 한 사람
                UserAccount beforeUserAccount = userAccountRepository.findByUserName(auction.getBidder());
                beforeUserAccount.setPoint(beforeUserAccount.getPoint()+auction.getPresentPrice());
                userAccountRepository.save(beforeUserAccount);
                userAccountLogRepository.save(UserAccountLog.of(null, beforeUserAccount.getUserEmail(), beforeUserAccount.getUserGrade(),
                        auction.getPresentPrice(), "충전", "경매/"+data[0]));
            }


            auction.setBidder(data[1]);
            auction.setPresentPrice(Integer.parseInt(data[2]));
            auction.setBiddingCount(auction.getBiddingCount()+1);
            auctionRepository.save(auction);
            auctionLogRepository.save(AuctionLog.of(data[1], auction.getId(), auction.getProduct().getId(),
                    auction.getProduct().getAppraisal().getAppraisalRequest().getAppraisalProductName(), Integer.parseInt(data[2])));
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