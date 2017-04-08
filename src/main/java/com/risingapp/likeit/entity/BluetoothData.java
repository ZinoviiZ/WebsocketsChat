package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
@Entity
public class BluetoothData {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne(mappedBy = "bluetoothData", fetch = FetchType.LAZY)
    private User user;
}
