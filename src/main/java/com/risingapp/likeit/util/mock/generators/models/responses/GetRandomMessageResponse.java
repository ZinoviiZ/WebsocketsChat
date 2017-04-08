package com.risingapp.likeit.util.mock.generators.models.responses;

import com.risingapp.likeit.util.mock.generators.models.RandomMessage;
import lombok.Data;

import java.util.List;

/**
 * Created by oleg on 08.04.17.
 */
@Data
public class GetRandomMessageResponse {
    public static final String SUCCESS = "success";
    String type;
    List<RandomMessage> value;
}
