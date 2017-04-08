package com.risingapp.likeit.repository;

import com.risingapp.likeit.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Repository
public interface RoomRepository extends JpaRepository<ChatRoom, Long> {
}
