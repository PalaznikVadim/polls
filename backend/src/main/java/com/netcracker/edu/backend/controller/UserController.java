package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<User> getAll(@RequestParam Integer page,Integer size,String sort,String order){
        System.out.println("page="+page+"size="+size+"sort="+sort+"order="+order );

        Pageable pageable= PageRequest.of(page,size, Sort.by(sort).ascending());
        return userService.findAll(pageable);
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
