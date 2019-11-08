package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Answer;
import com.netcracker.edu.backend.repository.AnswerRepository;
import com.netcracker.edu.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer[] getAllAnswerByQuestionId(Integer idQuestion) {
        return  answerRepository.findAllByIdQuestion(idQuestion);
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteById(Integer id) {
        answerRepository.deleteById(id);
    }

    @Override
    public Iterable<Answer> saveAllAnswers(List<Answer> answers) {
        return answerRepository.saveAll(answers);
    }


}
