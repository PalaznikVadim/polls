package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.TypeQuestion;

public interface TypeQuestionService {
    TypeQuestion[] getAllTypes();
    TypeQuestion getById(Integer id);
    Integer getIdByType(String id);
}
