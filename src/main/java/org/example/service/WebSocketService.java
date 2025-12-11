package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.WsMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Надсилає повідомлення всім клієнтам, які підписані на:
     *   /topic/group.{groupId}
     */
    public void sendToGroup(Long groupId, String type, Object payload) {

        WsMessage msg = new WsMessage(type, payload);

        String destination = "/topic/group." + groupId;

        System.out.println(">>> WS SEND to " + destination + " | type=" + type);

        messagingTemplate.convertAndSend(destination, msg);
    }
}
