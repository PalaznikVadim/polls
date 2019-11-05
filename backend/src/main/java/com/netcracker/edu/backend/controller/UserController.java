package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByEmailAndPassword(@RequestParam String email,@RequestParam String password) {
        User user = userService.findByEmailAndPassword(email,password);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return (List<User>) userService.findAll();
    }

    @RequestMapping(value = "id", method = RequestMethod.GET)
    public Optional<User> getUserById(@RequestParam String id) {
        return userService.findUserById(Integer.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.save(user);
    }
}
