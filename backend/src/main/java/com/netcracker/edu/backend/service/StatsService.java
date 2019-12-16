package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Stats;

public interface StatsService {
    Stats getByIdAnswer(int idAnswer);

    Stats save(Stats stats);
}
