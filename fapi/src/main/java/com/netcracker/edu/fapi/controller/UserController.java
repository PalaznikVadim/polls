package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/signin")
    public User getUserByEmail(@RequestParam String email,@RequestParam String password) {
        return userService.findByEmailAndPassword(email,password);
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user){
        System.out.println(user.toString());
        return userService.save(user);
    }

    @GetMapping("/id")
    public User getUserById(@RequestParam String id){
        return userService.findById(Integer.valueOf(id));
    }

    @GetMapping("/all")
    public User[] getAll(){return userService.getAll();}
}
