package com.risingapp.likeit.util.mock.generators.models.responses;

import com.risingapp.likeit.util.mock.generators.models.RandomUserResponse;
import lombok.Data;

import java.util.List;

/**
 * Created by oleg on 31.03.17.
 */
@Data
public class GetRandomUsersResponse {
    List<RandomUserResponse> results;
}
