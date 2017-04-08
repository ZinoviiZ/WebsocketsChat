package com.risingapp.likeit.repository;

import com.risingapp.likeit.entity.BluetoothData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oleg on 08.04.17.
 */
public interface BluetoothDataRepository extends JpaRepository<BluetoothData, Long> {
}
