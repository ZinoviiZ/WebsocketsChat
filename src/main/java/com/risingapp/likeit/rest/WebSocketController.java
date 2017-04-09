package com.risingapp.likeit.rest;

import com.risingapp.likeit.convertor.request.HelloMessage;
import com.risingapp.likeit.execption.SessionTimeOutException;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.model.request.SendMessageRequest;
import com.risingapp.likeit.model.response.SendMessageResponse;
import com.risingapp.likeit.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired private WebSocketService webSocketService;

    @PermitAll
    @MessageMapping("/chat/{id}")
    @SendTo("/chat/{id}")
    public MessageResponse onMessageEvent(@DestinationVariable("id") Long id, SendMessageRequest request) throws SessionTimeOutException {
        return webSocketService.sendMessage(id, request);
    }

    @PermitAll
    @MessageMapping("/user/{id}")
    @SendTo("/user/{id}")
    public Object onUserEvent(@DestinationVariable("id") Long id, Object request) {

        return null;
    }


}
