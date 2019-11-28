package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import com.netcracker.edu.fapi.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(@RequestParam int page,
                                                  @RequestParam int size,
                                                  @RequestParam String sort,
                                                  @RequestParam String order){

       return userService.findAll(page,size,sort,order);
    }

    @GetMapping("/signin")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email,@RequestParam String password) {
        return userService.findByEmailAndPassword(email,password);
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/id")
    public User getUserById(@RequestParam String id){
        return userService.findById(Integer.valueOf(id));
    }

    @GetMapping("/all")
    public User[] getAll(){return userService.getAll();}
}
