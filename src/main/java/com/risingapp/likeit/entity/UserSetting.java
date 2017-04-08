package com.risingapp.likeit.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zinoviyzubko on 08.04.17.
 */
@Data
@Entity
public class UserSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private boolean showEmail;
    private boolean showBirthday;
    private boolean showPhoneNumber;
}
