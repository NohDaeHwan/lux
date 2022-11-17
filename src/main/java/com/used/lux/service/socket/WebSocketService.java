package com.used.lux.service.socket;

import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.auction.AuctionLog;
import com.used.lux.repository.auction.AuctionLogRepository;
import com.used.lux.repository.auction.AuctionRepository;
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

    WebSocketService(@Autowired AuctionRepository auctionRepository,@Autowired AuctionLogRepository auctionLogRepository) {
        this.auctionRepository = auctionRepository;
        this.auctionLogRepository = auctionLogRepository;
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