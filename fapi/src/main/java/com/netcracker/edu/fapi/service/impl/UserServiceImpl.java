package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Value("${backend.server.url}")
    private String backendServerUrl;




    @Override
    public ResponseEntity<?> findAll(String search, Integer page, Integer size, String sort, String order) {
        RestTemplate restTemplate = new RestTemplate();
        Page<User> users = restTemplate.getForObject(backendServerUrl + "/api/user?search=" + search +
                "&page=" + page + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);
        if (users.getContent() != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public User save(User user) {
//        DataBinder dataBinder = new DataBinder(user);
//        dataBinder.addValidators(userValidator);
//        dataBinder.validate();
//
//        if (dataBinder.getBindingResult().hasErrors()) {
//            List<ObjectError> errorList = dataBinder.getBindingResult().getAllErrors();
//            return ResponseEntity.badRequest().body(errorList);
//        } else {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            RestTemplate restTemplate = new RestTemplate();
            user = restTemplate.postForEntity(backendServerUrl + "/api/user", user, User.class).getBody();
            return user;
//        }
    }

    @Override
    public User findById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(backendServerUrl + "api/user/id?id=" + id, User.class);
        return user;
    }

    @Override
    public User[] getAll() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/user/all", User[].class);
    }

    @Override
    public User getByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/user/username?email=" + email, User.class);
    }

}

