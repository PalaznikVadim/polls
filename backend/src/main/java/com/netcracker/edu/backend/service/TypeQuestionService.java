package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.TypeQuestion;
import com.netcracker.edu.backend.entity.enums.Type;

import java.util.List;
import java.util.Optional;

public interface TypeQuestionService {
    List<TypeQuestion> getAllTypeQuestion();

    Optional<TypeQuestion> getTypeById(int id);

    int getIdByType(Type type);

    List<Type> getAllType();
}
