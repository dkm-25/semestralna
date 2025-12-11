package org.example.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String handleMessage(String msg) {
        return "Server received: " + msg;
    }
}
