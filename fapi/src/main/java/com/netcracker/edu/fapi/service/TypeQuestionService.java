package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.TypeQuestion;

import java.util.List;

public interface TypeQuestionService {
    List<TypeQuestion> getAllTypes();

    TypeQuestion getById(int id);

    int getIdByType(String type);

    List<String> getAll();
}
