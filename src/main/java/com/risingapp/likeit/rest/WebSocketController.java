package com.risingapp.likeit.rest;

import com.risingapp.likeit.convertor.request.HelloMessage;
import com.risingapp.likeit.model.request.SendMessageRequest;
import com.risingapp.likeit.model.response.SendMessageResponse;
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
    public SendMessageResponse greeting(@DestinationVariable("id") Long id, SendMessageRequest request) {

        return null;
    }


}
