package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.User;

import java.util.List;

public interface PollService {

    Poll findById(Integer id);
    List<Poll> findAllByUser(User user);
    Poll save(Poll poll);
    void deletePoll(Integer id);
}
