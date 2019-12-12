package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.TokenService;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(@RequestParam String search,
                                         int page,
                                         int size,
                                         String sort,
                                         String order) {

        return userService.findAll(search, page, size, sort, order);
    }

    @GetMapping("/signin")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email, @RequestParam String password) {

        return tokenService.authenticate(email, password);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return tokenService.registration(user);
    }

    @GetMapping("/id")
    public User getUserById(@RequestParam String id) {
        return userService.findById(Integer.valueOf(id));
    }

    @Secured(value = {"ADMIN"})
    @GetMapping("/all")
    public User[] getAll() {
        return userService.getAll();
    }

    @GetMapping("/loadByToken")
    public User loadByToken(@RequestParam String token) {
        return tokenService.loadByToken(token);
    }

}
