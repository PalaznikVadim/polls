package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Stats;

public interface StatsService {
    Stats getByIdAnswer(Integer idAnswer);
    Stats save(Stats stats);

}
