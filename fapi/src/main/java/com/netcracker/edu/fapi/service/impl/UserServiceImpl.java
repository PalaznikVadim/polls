package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public User findByEmailAndPassword(String email,String password) {
        RestTemplate restTemplate = new RestTemplate();
        User user=restTemplate.getForObject(backendServerUrl+"api/user/email?email="+email+"&password="+password,User.class);
        return user;
    }

    @Override
    public List<User> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/user", User[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    @Override
    public User save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/user", user, User.class).getBody();
    }

    @Override
    public User findById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        User user=restTemplate.getForObject(backendServerUrl+"api/user/id?id="+id,User.class);
        return user;
    }
}
