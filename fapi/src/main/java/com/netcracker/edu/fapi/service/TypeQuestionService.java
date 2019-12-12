package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.TypeQuestion;

import java.util.List;

public interface TypeQuestionService {
    TypeQuestion[] getAllTypes();
    TypeQuestion getById(Integer id);
    Integer getIdByType(String id);
    List<String> getAll();
}
