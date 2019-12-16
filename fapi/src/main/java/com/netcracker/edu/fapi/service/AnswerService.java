package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> getAllAnswerByQuestionId(int idQuestion);

    Answer saveAnswer(Answer answer);

    void deleteById(int id);

    Answer getById(int id);
}
