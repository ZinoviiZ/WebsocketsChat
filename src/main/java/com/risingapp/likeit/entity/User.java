package com.risingapp.likeit.entity;

import com.risingapp.likeit.enums.UserRole;
import lombok.Data;

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
    protected String registrationDay;
    protected String vkToken;

    protected Long photoId;
    @Enumerated(EnumType.STRING)
    protected UserRole userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Photo> photos;
}
