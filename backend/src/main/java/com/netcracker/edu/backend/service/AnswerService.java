package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer[] getAllAnswerByQuestionId(Integer idQuestion);
    Answer saveAnswer(Answer answer);
    void deleteById(Integer id);
    Iterable<Answer> saveAllAnswers(List<Answer> answers);
}
