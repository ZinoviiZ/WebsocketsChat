package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
@Entity
public class UserSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Boolean showEmail;
    private Boolean showBirthday;
    private Boolean showPhoneNumber;


    @OneToOne(mappedBy = "setting", fetch = FetchType.LAZY)
    private User user;

    public UserSetting() {
        showEmail = false;
        showBirthday = false;
        showPhoneNumber = false;
    }

}
