package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Page<User> findAll(String search, Integer page,Integer size,String sort,String order) {
        return userRepository.findAllByRole(search,createPageable(page,size,sort,order));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    private Pageable createPageable(Integer page, Integer size, String sort, String order){
        Pageable pageable;
        System.out.println("page="+page+" size="+size+" sort="+sort+" order="+order);
        if(order.toLowerCase().contains("asc")) {
            pageable = PageRequest.of(Integer.valueOf(page), size, Sort.by(sort).ascending());
        }else{
            pageable=PageRequest.of(Integer.valueOf(page), size, Sort.by(sort).descending());
        }
        return pageable;
    }
}
