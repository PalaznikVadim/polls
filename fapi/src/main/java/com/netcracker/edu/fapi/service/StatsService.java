package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Stats;

public interface StatsService {

    Stats getByIdAnswer(int id);

    Stats save(Stats stats);
}
