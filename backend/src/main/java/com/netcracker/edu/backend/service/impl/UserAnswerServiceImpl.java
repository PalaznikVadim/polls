package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.UserAnswer;
import com.netcracker.edu.backend.repository.UserAnswerRepository;
import com.netcracker.edu.backend.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Override
    public List<UserAnswer> saveAll(List<UserAnswer> userAnswers) {
        return (List<UserAnswer>) userAnswerRepository.saveAll(userAnswers);
    }

    @Override
    public int getCountSelected(int idAnswer) {
        return userAnswerRepository.countUserAnswerByIdAnswer(idAnswer);
    }

    @Override
    public int getCountAllSelected(int idQuestion) {
        return userAnswerRepository.countUserAnswerByIdQuestion(idQuestion);
    }
}
