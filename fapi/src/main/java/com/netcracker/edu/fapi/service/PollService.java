package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Poll;

public interface PollService {

    Poll findById(Integer id);
    Poll[] findAllByUserId(Integer userId);
    Poll save(Poll poll);
    void deletePoll(Integer id);
}
