package com.risingapp.likeit.repository;
import com.risingapp.likeit.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oleg on 08.04.17.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
