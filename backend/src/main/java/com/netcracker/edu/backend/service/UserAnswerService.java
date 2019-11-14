package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.UserAnswer;

import java.util.List;

public interface UserAnswerService {

    List<UserAnswer> saveAll(List<UserAnswer> userAnswers);
    Integer getCountSelected(Integer idAnswer);
    Integer getCountAllSelected(Integer idQuestion);
}
