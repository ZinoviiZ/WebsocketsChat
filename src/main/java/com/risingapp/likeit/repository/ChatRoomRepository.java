package com.risingapp.likeit.repository;

import com.risingapp.likeit.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oleg on 08.04.17.
 */
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
