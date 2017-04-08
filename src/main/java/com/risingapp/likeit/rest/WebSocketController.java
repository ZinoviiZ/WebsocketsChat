package com.risingapp.likeit.rest;

import com.risingapp.likeit.convertor.request.HelloMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Controller
public class WebSocketController {

    @MessageMapping("/chat/{id}")
    @SendTo("/topic/{id}")
    public HelloMessage greeting(@DestinationVariable("id") Long id, HelloMessage message) {
        message.setName("Hello " + message.getName() + "!");
        return message;
    }
}
