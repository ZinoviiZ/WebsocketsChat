package com.risingapp.likeit.util.mock;

import lombok.Data;

import java.util.List;

/**
 * Created by oleg on 31.03.17.
 */
@Data
public class GetRandomUsersResponse {
    List<RandomUserResponse> results;
}
