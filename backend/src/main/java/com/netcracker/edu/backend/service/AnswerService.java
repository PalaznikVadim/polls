package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    Answer[] getAllAnswerByQuestionId(Integer idQuestion);
    Answer saveAnswer(Answer answer);
    void deleteById(Integer id);
    Iterable<Answer> saveAllAnswers(List<Answer> answers);
    Optional<Answer> getById(Integer id);
}
