package vn.edu.hust.project.crossplatform.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("New connection established");
    }


    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {

    }

    @EventListener
    public void handleWebSocketConnectException(SessionDisconnectEvent event) {
    }

    @EventListener
    public void handleSessionTimeout(SessionDisconnectEvent event) {
        log.warn("WebSocket connection timeout or disconnected.");
    }
}
