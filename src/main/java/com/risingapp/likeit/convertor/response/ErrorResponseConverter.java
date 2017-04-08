package com.risingapp.likeit.convertor.response;

import com.risingapp.likeit.enums.ErrorStatus;
import com.risingapp.likeit.model.common.MessageResponse;
import org.springframework.stereotype.Component;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Component
public class ErrorResponseConverter {

    public MessageResponse build(ErrorStatus errorStatus) {
        return new MessageResponse(errorStatus);
    }
}