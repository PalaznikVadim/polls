package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Answer;
import com.netcracker.edu.backend.repository.AnswerRepository;
import com.netcracker.edu.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAllAnswerByQuestionId(int idQuestion) {
        return answerRepository.findAllByIdQuestion(idQuestion);
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteById(int id) {
        answerRepository.deleteById(id);
    }

    @Override
    public Iterable<Answer> saveAllAnswers(List<Answer> answers) {
        return answerRepository.saveAll(answers);
    }

    @Override
    public Optional<Answer> getById(int id) {
        return answerRepository.findById(id);
    }


}
