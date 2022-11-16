package com.used.lux.service.socket;

import com.used.lux.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.server.ServerEndpoint;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
@ServerEndpoint(value="/auction/{auctionId}/bidder")
public class WebSocketService extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();

    private final AuctionRepository auctionRepository;

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