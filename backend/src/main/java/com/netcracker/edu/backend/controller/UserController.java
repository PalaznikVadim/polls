package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Page<User> getAll(@RequestParam String search, Integer page,Integer size,String sort,String order){
        return userService.findAll(search,page,size,sort,order);
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

    //email as username
    @RequestMapping(value = "/username",method = RequestMethod.GET)
    public User getByEmail(@RequestParam String email){
        System.out.println("email- " + email);
        User user =  userService.getByEmail(email);
        System.out.println("user- " + user);
        return user;
    }
}
