package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Data
@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(columnDefinition="TEXT")
    private String base64;
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
