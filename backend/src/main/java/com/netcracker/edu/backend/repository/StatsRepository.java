package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Stats;
import org.springframework.data.repository.CrudRepository;

public interface StatsRepository extends CrudRepository<Stats,Integer> {

    Stats findByIdAnswer(Integer idAnswer);

}
