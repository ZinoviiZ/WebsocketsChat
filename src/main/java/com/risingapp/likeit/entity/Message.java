package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(columnDefinition="TEXT")
    private String text;
    private String createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
}
