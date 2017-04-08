package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    private List<Attachment> attachments;

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    private List<MessageLike> likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Message() {
        attachments = new ArrayList<>();
        likes = new ArrayList<>();
    }
}
