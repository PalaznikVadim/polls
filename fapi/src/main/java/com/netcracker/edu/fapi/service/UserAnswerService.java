package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserAnswer;
import org.springframework.http.ResponseEntity;

public interface UserAnswerService {

    ResponseEntity<UserAnswer> save(UserAnswer userAnswer);
    Integer countSelected(Integer idAnswer);
}
