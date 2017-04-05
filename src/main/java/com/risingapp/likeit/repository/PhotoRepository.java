package com.risingapp.likeit.repository;

import com.risingapp.likeit.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
