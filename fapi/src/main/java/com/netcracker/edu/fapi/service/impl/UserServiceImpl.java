package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import com.netcracker.edu.fapi.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private UserValidator userValidator;

    @Override
    public ResponseEntity<?> findByEmailAndPassword(String email,String password) {
        Map<String,List<String>> errors=validateEmailAndPassword(email,password);
        if(errors.size()!=0){
            return ResponseEntity.badRequest().body(errors);
        }else {
            RestTemplate restTemplate = new RestTemplate();
            User user = restTemplate.getForObject(backendServerUrl + "api/user/email?email=" + email + "&password=" + password, User.class);
            return ResponseEntity.ok().body(user);
        }
    }

    @Override
    public ResponseEntity<?> findAll(Integer page, Integer size, String sort, String order) {
        RestTemplate restTemplate=new RestTemplate();
        Page<User> users=restTemplate.getForObject(backendServerUrl+"/api/user?page="+page
                +"&size="+size+"&sort="+sort+"&order="+order, RestPageImpl.class);
        if(users.getContent()!=null){
            return ResponseEntity.ok(users);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public ResponseEntity<?> save(User user) {
        DataBinder dataBinder=new DataBinder(user);
        dataBinder.addValidators(userValidator);
        dataBinder.validate();

        if(dataBinder.getBindingResult().hasErrors()){
            List<ObjectError> errorList= dataBinder.getBindingResult().getAllErrors();
            return ResponseEntity.badRequest().body(errorList);
        }
        else {
            RestTemplate restTemplate = new RestTemplate();
            user = restTemplate.postForEntity(backendServerUrl + "/api/user", user, User.class).getBody();
            return ResponseEntity.ok().body(user);
        }
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

    private Map<String,List<String>> validateEmailAndPassword(String email, String password){
        Map<String,List<String>> errors=new HashMap<String, List<String>>();
        List<String> emailErrors=new ArrayList<>();
        List<String> passwordErrors=new ArrayList<>();
        String regExEmail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(!email.matches(regExEmail)){
            emailErrors.add("Incorrect format email (example: cat@gmail.com)");
        }
        if(email.length()>25){
            emailErrors.add("Incorrect email length (length<26)");
        }

        if(password.length()>25){
            passwordErrors.add("Incorrect password length (length<26)");
        }

        if(password.contains(" ")){
            passwordErrors.add("Password has whitespaces");
        }

        if(emailErrors.size()!=0)
            errors.put("email",emailErrors);
        if(passwordErrors.size()!=0)
            errors.put("password",passwordErrors);

        return errors;
    }
}

