package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestionByIdPoll(Integer idPoll);
    Question getById(Integer id);
    Question save(Question question);
    void delete(Integer id);
}
