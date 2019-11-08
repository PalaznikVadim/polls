package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Integer> {
    List<Question> findAllByIdPoll(Integer idPoll);

//    @Query('update Question set ')
//    Question updateQuestion(Question question);
}
