package io.skcloud.goblintest.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String clientMessage = message.getPayload();
        System.out.println("Received message: " + clientMessage);
        // 여기서 클라이언트에게 메시지를 보낼 수도 있습니다.
        session.sendMessage(new TextMessage("Echo: " + clientMessage));
    }
}
