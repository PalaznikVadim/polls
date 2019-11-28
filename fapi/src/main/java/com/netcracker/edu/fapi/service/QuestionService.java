package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    List<ViewQuestion> getAllQuestionByIdPoll(Integer idPoll);
    ViewQuestion getById(Integer id);
    ResponseEntity<?> save(ViewQuestion question);
    void delete(Integer id);
    void updateStatsQuestion(Integer id);
    List<ViewQuestion> getPollStats( Integer idPoll);
}
