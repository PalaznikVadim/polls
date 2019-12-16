package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> getAllQuestionByIdPoll(int idPoll);

    Optional<Question> getById(int id);

    Question save(Question question);

    void delete(int id);
}
