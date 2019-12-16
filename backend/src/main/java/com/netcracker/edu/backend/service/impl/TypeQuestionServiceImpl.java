package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.TypeQuestion;
import com.netcracker.edu.backend.entity.enums.Type;
import com.netcracker.edu.backend.repository.TypeQuestionRepository;
import com.netcracker.edu.backend.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeQuestionServiceImpl implements TypeQuestionService {

    @Autowired
    private TypeQuestionRepository typeQuestionRepository;

    @Override
    public List<TypeQuestion> getAllTypeQuestion() {
        return (List<TypeQuestion>) typeQuestionRepository.findAll();
    }

    @Override
    public Optional<TypeQuestion> getTypeById(int id) {
        return typeQuestionRepository.findById(id);
    }

    @Override
    public int getIdByType(Type type) {

        return typeQuestionRepository.findIdByType(type);
    }

    @Override
    public List<Type> getAllType() {
        return typeQuestionRepository.getAllType();
    }
}
