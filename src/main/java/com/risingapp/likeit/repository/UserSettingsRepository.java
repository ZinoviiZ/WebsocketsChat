package com.risingapp.likeit.repository;

import com.risingapp.likeit.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oleg on 08.04.17.
 */
public interface UserSettingsRepository extends JpaRepository<UserSetting, Long> {
}
