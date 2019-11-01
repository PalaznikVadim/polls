package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Integer> {
}
