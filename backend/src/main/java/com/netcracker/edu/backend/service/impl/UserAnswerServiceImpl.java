package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.UserAnswer;
import com.netcracker.edu.backend.repository.UserAnswerRepository;
import com.netcracker.edu.backend.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Override
    public UserAnswer save(UserAnswer userAnswer) {
        return userAnswerRepository.save(userAnswer);
    }

    @Override
    public Integer getCountSelected(Integer idAnswer) {
        return userAnswerRepository.countUserAnswerByIdAnswer(idAnswer);
    }
}
