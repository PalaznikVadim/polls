package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.PollRepository;
import com.netcracker.edu.backend.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public Optional<Poll> findPollById(Integer id) {
        return pollRepository.findById(id);
    }

    @Override
    public List<Poll> findAllByUSerId(Integer userId) {
        return (List<Poll>) pollRepository.findPollByUserByIdUser_Id(userId);
    }

    @Override
    public Poll save(Poll poll) {
        return pollRepository.save(poll);
    }
}
