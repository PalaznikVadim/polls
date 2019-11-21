package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.entity.enums.Role;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmailAndPassword(String email,String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

//    @Override
//    public Page<User> findAll(Integer page, String sortedField, Pageable pageable) {
//        if (sortedField==null){
//            sortedField="name";
//        }
//        Pageable pageWithTenElements=PageRequest.of(page,10, Sort.by(sortedField));
//
//        return userRepository.findAllByRole(Role.user, pageable);
//    }

    public Page<User> findAll(Pageable pageable){
        return userRepository.findAllByRole(Role.user,pageable);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }
}
