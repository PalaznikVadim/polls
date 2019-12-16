package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.UserAnswer;

import java.util.List;

public interface UserAnswerService {

    List<UserAnswer> saveAll(List<UserAnswer> userAnswers);

    int getCountSelected(int idAnswer);

    int getCountAllSelected(int idQuestion);
}
