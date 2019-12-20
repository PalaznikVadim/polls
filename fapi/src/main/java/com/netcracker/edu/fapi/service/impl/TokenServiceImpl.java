package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.security.jwt.JwtTokenProvider;
import com.netcracker.edu.fapi.service.TokenService;
import com.netcracker.edu.fapi.service.UserService;
import com.netcracker.edu.fapi.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @Override
    public ResponseEntity<?> authenticate(String email, String password) {
        Map<String, List<String>> errors = validateEmailAndPassword(email, password);
        if (errors.size() != 0) {
            return ResponseEntity.badRequest().body(errors);
        } else {
            try {
                String username = email;
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                User user = userService.getByEmail(username);

                if (user == null) {
                    throw new UsernameNotFoundException("User with email: " + username + " not found");

                }
                String token = jwtTokenProvider.createToken(username, user.getRole());

                Map<Object, Object> response = new HashMap<>();
                response.put("username", username);
                response.put("user", user);
                response.put("token", token);

                return ResponseEntity.ok(response);
            } catch (AuthenticationException e) {
                throw new BadCredentialsException("Invalid username or password");
            }
        }
    }

    @Override
    public ResponseEntity<?> registration(User user) {
        DataBinder dataBinder = new DataBinder(user);
        dataBinder.addValidators(userValidator);
        dataBinder.validate();

        if (dataBinder.getBindingResult().hasErrors()) {
            List<ObjectError> errorList = dataBinder.getBindingResult().getAllErrors();
            return ResponseEntity.badRequest().body(errorList);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userService.save(user);
            return ResponseEntity.ok().body(user);
        }
    }

    @Override
    public User loadByToken(String token) {
        return userService.getByEmail(jwtTokenProvider.getUsername(token));
    }

    private Map<String, List<String>> validateEmailAndPassword(String email, String password) {

        Map<String, List<String>> errors = new HashMap<String, List<String>>();
        List<String> emailErrors = new ArrayList<>();
        List<String> passwordErrors = new ArrayList<>();
        String regExEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";
        if (!email.matches(regExEmail)) {
            emailErrors.add("Incorrect format email (example: cat@gmail.com)");
        }
        if (email.length() > 25) {
            emailErrors.add("Incorrect email length (length<26)");
        }

        if (password.length() > 25) {
            passwordErrors.add("Incorrect password length (length<26)");
        }

        if (password == "") {
            passwordErrors.add("Password is empty!");
        }

        if (password.contains(" ")) {
            passwordErrors.add("Password has whitespaces");
        }

        if (emailErrors.size() != 0)
            errors.put("email", emailErrors);
        if (passwordErrors.size() != 0)
            errors.put("password", passwordErrors);

        return errors;
    }
}
