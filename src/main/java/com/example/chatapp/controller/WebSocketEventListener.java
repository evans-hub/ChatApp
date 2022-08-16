package com.example.chatapp.controller;

import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.model.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
@Slf4j
public class WebSocketEventListener {
    private static final Logger logger=LoggerFactory.getLogger(WebSocketEventListener.class);
    @Autowired
    private SimpMessageSendingOperations operations;
    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent event){
        logger.info("New Conection");
    }
    @EventListener
    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event){
        final StompHeaderAccessor stompHeaderAccessor=StompHeaderAccessor.wrap(event.getMessage());
        final String username=(String) stompHeaderAccessor.getSessionAttributes().get("username");
        final ChatMessage chatMessage=ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();
        operations.convertAndSend("/topic/public",chatMessage);
    }
}
