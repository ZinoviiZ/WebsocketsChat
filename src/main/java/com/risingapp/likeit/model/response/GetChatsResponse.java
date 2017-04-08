package com.risingapp.likeit.model.response;

import com.risingapp.likeit.model.common.ApiResponse;
import lombok.Data;

import java.util.List;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
public class GetChatsResponse extends ApiResponse {

    private Boolean last;
    private List<ChatResponse> chats;
}
