package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
@Entity
public class MessageLike {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;
}
