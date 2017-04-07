package com.risingapp.likeit.model.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@Data
public class MessageResponse {

    private int statusCode;
    private String errorMessage;
}
