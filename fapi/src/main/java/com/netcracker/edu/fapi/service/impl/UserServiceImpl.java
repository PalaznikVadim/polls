package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public Page<User> findAll(Integer page, Integer size, String sort, String order) {
//        Pageable pageable;
//        if(order.equals("DESK")) {
//            pageable=PageRequest.of(page,size,Sort.by(sort).descending());
//        }else if(order.equals("ASC")){
//            pageable=PageRequest.of(page,size,Sort.by(sort).ascending());
//        }else{
//            pageable=PageRequest.of(page,size);
//        }

        RestTemplate restTemplate=new RestTemplate();

        return restTemplate.getForObject(backendServerUrl+"/api/user?page="+page
                +"&size="+size+"&sort="+sort+"&order="+order, RestPageImpl.class);


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

    @Override
    public User[] getAll() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/user/all",User[].class);
    }
}
