package com.risingapp.likeit.repository;

import com.risingapp.likeit.entity.NetworkData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oleg on 08.04.17.
 */
public interface NetworkDataRepository extends JpaRepository<NetworkData, Long> {
}
