package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Question;
import com.netcracker.edu.backend.repository.QuestionRepository;
import com.netcracker.edu.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestionByIdPoll(Integer idPoll) {
        return questionRepository.findAllByIdPoll(idPoll);
    }

    @Override
    public Optional<Question> getById(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void delete(Integer id) {
        questionRepository.deleteById(id);
    }
}
