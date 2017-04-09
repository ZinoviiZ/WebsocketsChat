package com.risingapp.likeit.rest;

import com.risingapp.likeit.execption.NotEnoughChatRoomsException;
import com.risingapp.likeit.execption.NotEnoughMessagesException;
import com.risingapp.likeit.execption.SessionTimeOutException;
import com.risingapp.likeit.model.common.MessageResponse;
import com.risingapp.likeit.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@RestController
@RequestMapping(value = "/rest/rooms")
public class RoomController {

    @Autowired private ChatRoomService chatService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public MessageResponse getChats(@RequestParam("offset") Long chatId,
                                    @RequestParam("count") int count) throws SessionTimeOutException, NotEnoughChatRoomsException {
        return chatService.getChats(chatId, count);
    }

    @RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
    public MessageResponse getMessages(@PathVariable("id") Long roomId,
                                       @RequestParam("offset") int offSet,
                                       @RequestParam("count") int count) throws SessionTimeOutException, NotEnoughMessagesException {
        return chatService.getMessages(roomId, offSet, count);
    }
}
