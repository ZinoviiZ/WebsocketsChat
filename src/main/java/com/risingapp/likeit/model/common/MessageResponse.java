package com.risingapp.likeit.model.common;

import com.risingapp.likeit.enums.ErrorStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Data
@NoArgsConstructor
public class MessageResponse <T extends ApiResponse> {

    private int statusCode;
    private String errorMessage;
    private T data;

    public MessageResponse(ErrorStatus errorStatus) {
        this.statusCode = errorStatus.getCode();
        this.errorMessage = errorStatus.getMessage();
    }

    public MessageResponse(T data) {
        this.data = data;
    }
}
