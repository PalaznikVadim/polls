package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.UserAnswer;

public interface UserAnswerService {

    UserAnswer save(UserAnswer userAnswer);
    Integer getCountSelected(Integer idAnswer);
}
