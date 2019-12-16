package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.TypeQuestion;
import com.netcracker.edu.backend.entity.enums.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TypeQuestionRepository extends CrudRepository<TypeQuestion, Integer> {

    @Query(value = "select t.id from TypeQuestion t where t.type =:type ")
    int findIdByType(@Param("type") Type type);

    @Query("select t.type from TypeQuestion t")
    List<Type> getAllType();
}

