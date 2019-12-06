package com.netcracker.edu.fapi.security.jwt;

import com.netcracker.edu.fapi.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user){

        return new JwtUser(
          user.getId(),
          user.getEmail(),
          user.getName(),
          user.getSurname(),
          user.getPassword(),
                true,
                mapToGrantedAuthorities(new ArrayList<>(Arrays.asList("ROLE_" + user.getRole().toUpperCase())))

        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles){
        return userRoles.stream().map(role->
                new SimpleGrantedAuthority(role)
                ).collect(Collectors.toList());
    }

}
