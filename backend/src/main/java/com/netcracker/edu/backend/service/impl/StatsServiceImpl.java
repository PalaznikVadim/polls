package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Stats;
import com.netcracker.edu.backend.repository.StatsRepository;
import com.netcracker.edu.backend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public Stats getByIdAnswer(int idAnswer) {
        return statsRepository.findByIdAnswer(idAnswer);
    }

    @Override
    public Stats save(Stats stats) {
        System.out.println(stats.toString());
        return statsRepository.save(stats);
    }
}
