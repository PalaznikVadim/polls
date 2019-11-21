package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.repository.PollRepository;
import com.netcracker.edu.backend.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Poll> findAllByUserId(Integer userId, Pageable pageable) {
        return pollRepository.findPollsByIdUser(userId,pageable);
    }

    @Override
    public List<Poll> findAllTemplateByTheme(String theme) {
        return (List<Poll>) pollRepository.findAllTemplateByTheme(theme);
    }

    @Override
    public Poll save(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public void delete(Integer id) {
         pollRepository.deleteById(id);
    }

    @Override
    public Poll findByLink(String link) {
        return pollRepository.findByLink(link);
    }
}
