package com.risingapp.likeit.rest;

import com.risingapp.likeit.convertor.request.HelloMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.security.PermitAll;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Controller
public class WebSocketController {

    @PermitAll
    @MessageMapping("/chat/{id}")
    @SendTo("/chat/{id}")
    public HelloMessage greeting(@DestinationVariable("id") Long id, HelloMessage message) {
        message.setName("Hello " + message.getName() + "!");
        return message;
    }
}
