package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface PollService {
    Optional<Poll> findPollById(Integer id);
    List<Poll> findAllByUserId(Integer userId);
    Poll save(Poll poll);
    void delete(Integer id);



}
