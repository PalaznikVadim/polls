package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    List<Answer> getAllAnswerByQuestionId(int idQuestion);

    Answer saveAnswer(Answer answer);

    void deleteById(int id);

    Iterable<Answer> saveAllAnswers(List<Answer> answers);

    Optional<Answer> getById(int id);
}
