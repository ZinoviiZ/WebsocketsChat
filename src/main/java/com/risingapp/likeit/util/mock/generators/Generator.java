package com.risingapp.likeit.util.mock.generators;

import com.risingapp.likeit.entity.ChatRoom;

import java.util.List;

/**
 * Created by oleg on 07.04.17.
 */
public abstract class Generator<T> {
    public abstract T generateObject();
    public abstract List<T> generateObjects(int count, ChatRoom chatRoom);
}
