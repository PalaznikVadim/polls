package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    List<ViewQuestion> getAllQuestionByIdPoll(int idPoll);

    ViewQuestion getById(int id);

    ResponseEntity<?> save(ViewQuestion question);

    void delete(int id);

    void updateStatsQuestion(int id);

    List<ViewQuestion> getPollStats(int idPoll);
}
