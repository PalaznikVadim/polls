package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserAnswer;

import java.util.List;

public interface UserAnswerService {

    List<UserAnswer> saveAll(List<UserAnswer> userAnswers);

    int countSelected(int idAnswer);

    int countAllSelected(int idQuestion);
}
