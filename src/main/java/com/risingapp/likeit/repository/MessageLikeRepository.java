package com.risingapp.likeit.repository;

import com.risingapp.likeit.entity.MessageLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oleg on 08.04.17.
 */
public interface MessageLikeRepository extends JpaRepository<MessageLike, Long> {
}
