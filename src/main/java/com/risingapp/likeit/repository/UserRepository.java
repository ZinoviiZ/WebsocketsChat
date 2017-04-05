package com.risingapp.likeit.repository;

import com.risingapp.likeit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String emal);
}
