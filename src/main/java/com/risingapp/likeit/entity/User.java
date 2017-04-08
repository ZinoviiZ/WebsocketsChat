package com.risingapp.likeit.entity;

import com.risingapp.likeit.enums.UserRole;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    protected String email;
    protected String password;

    protected String firstName;
    protected String lastName;

    protected String birthday;
    protected String registrationDate;
    protected String vkToken;

    @Transient
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    protected UserRole userRole;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<ChatRoom> chatRooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bluetooth_data_id")
    private BluetoothData bluetoothData;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_data_id")
    private NetworkData networkData;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setting_id")
    private UserSetting setting;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<MessageLike> likes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Message> messages;
}