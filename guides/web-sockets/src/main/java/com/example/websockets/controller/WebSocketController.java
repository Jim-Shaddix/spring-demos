package com.example.websockets.controller;

import com.example.websockets.dto.WebSocketInputMessage;
import com.example.websockets.dto.WebSocketOutputMessage;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log
@Controller
public class WebSocketController {

    /**
     * - allows for websocket connections to be established on /chat.
     * - sends return messages to the /topic/messages channel.
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public WebSocketOutputMessage send(@Payload WebSocketInputMessage message) {
        System.out.println("Input Message: " + message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new WebSocketOutputMessage(message.getFrom(), message.getText(), time);
    }

}
