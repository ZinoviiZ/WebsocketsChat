package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
@Entity
public class NetworkData {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToMany(mappedBy = "networkData", fetch = FetchType.LAZY)
    private List<User> users;
}
