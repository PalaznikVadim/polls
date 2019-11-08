package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer,Integer> {
    Answer[] findAllByIdQuestion(Integer idQuestion);
}
