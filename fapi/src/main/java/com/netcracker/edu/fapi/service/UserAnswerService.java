package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserAnswer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserAnswerService {

    ResponseEntity<UserAnswer[]> saveAll(List<UserAnswer> userAnswers);
    Integer countSelected(Integer idAnswer);
    Integer countAllSelected(Integer idQuestion);
}
