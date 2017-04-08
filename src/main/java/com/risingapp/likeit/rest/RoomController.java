package com.risingapp.likeit.rest;

import com.risingapp.likeit.model.response.GetChatRoomMessagesResponse;
import com.risingapp.likeit.model.response.GetChatRoomsResponse;
import com.risingapp.likeit.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@RestController
@RequestMapping(value = "/rest/rooms")
public class RoomController {


    @Autowired private RoomService chatService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public GetChatRoomsResponse getChats(@RequestParam("offset") int offSet,
                                         @RequestParam("count") int count) {
        return chatService.getChats(offSet, count);
    }

    @RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
    public GetChatRoomMessagesResponse getMessages(@PathVariable("id") long chatId,
                                                   @RequestParam("offset") int offSet,
                                                   @RequestParam("count") int count) {
        return chatService.getMessages(chatId, offSet, count);
    }
}
