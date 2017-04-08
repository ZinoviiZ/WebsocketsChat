package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "ChatRoom_User",
            joinColumns = {@JoinColumn(name = "chat_room_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    private List<User> users;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<Message> messages;
}
