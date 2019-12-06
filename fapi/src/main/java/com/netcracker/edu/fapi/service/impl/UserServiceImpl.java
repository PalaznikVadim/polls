package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import com.netcracker.edu.fapi.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    private final BCryptPasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    @Autowired
    public UserServiceImpl( UserValidator userValidator, BCryptPasswordEncoder passwordEncoder) {
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ResponseEntity<?> findByEmailAndPassword(String email, String password) {

//        Map<String, List<String>> errors = validateEmailAndPassword(email, password);
//        if (errors.size() != 0) {
//            return ResponseEntity.badRequest().body(errors);
//        } else {
//            try {
//                String username = email;
//                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//                User user = getByEmail(username);
//
//
//                if (user == null) {
//                    throw new UsernameNotFoundException("User with email: " + username + " not found");
//                }
//                String token = jwtTokenProvider.createToken(username, user.getRole());
//
//                Map<Object, Object> response = new HashMap<>();
//                response.put("username", username);
//                response.put("token", token);
//
//                return ResponseEntity.ok(response);
//            } catch (AuthenticationException e) {
//                throw new BadCredentialsException("Invalid username or password");
//            }


//        Map<String,List<String>> errors=validateEmailAndPassword(email,password);
//        if(errors.size()!=0){
//            return ResponseEntity.badRequest().body(errors);
//        }else {
//            RestTemplate restTemplate = new RestTemplate();
//            User user = restTemplate.getForObject(backendServerUrl + "api/user/email?email=" + email + "&password=" + password, User.class);
//            return ResponseEntity.ok().body(user);
//        }
       //}
        return null;
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
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public User getByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/user/username?email="+email,User.class);
    }

}

