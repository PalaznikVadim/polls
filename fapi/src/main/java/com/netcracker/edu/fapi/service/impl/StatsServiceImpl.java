package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Stats;
import com.netcracker.edu.fapi.service.StatsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
public class StatsServiceImpl implements StatsService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Stats getByIdAnswer(Integer id) {
        RestTemplate restTemplate=new RestTemplate();
        Stats stats=restTemplate.getForObject(backendServerUrl+"api/stats/answer?idAnswer="+id,Stats.class);
        return stats;

    }

    @Override
    public Stats save(Stats stats) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl+"/api/stats",stats,Stats.class).getBody();
    }

}
