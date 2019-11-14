package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;

import java.util.List;

public interface AnswerService {
    List<Answer> getAllAnswerByQuestionId(Integer idQuestion);
    Answer saveAnswer(Answer answer);
    void deleteById(Integer id);
//    Iterable<Answer> saveAllAnswers(List<Answer> answers);
}
