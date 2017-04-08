package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String type;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;
}
