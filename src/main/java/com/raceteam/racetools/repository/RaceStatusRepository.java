package com.raceteam.racetools.repository;

import com.raceteam.racetools.entity.RaceStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceStatusRepository extends CrudRepository<RaceStatus,Integer> {
}
