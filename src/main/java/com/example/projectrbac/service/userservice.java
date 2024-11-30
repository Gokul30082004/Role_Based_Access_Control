package com.example.projectrbac.service;

import com.example.projectrbac.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.example.projectrbac.service.JwtService;
import com.example.projectrbac.repository.userRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class userservice {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public String isvaliduser(Users users){
        try {

            System.out.println("inside isvaliduser");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                System.out.println("admin  ----erglergknjsgdnkwjergfnksj");
                return jwtService.generateToken(users.getEmail());
            } else {
                System.out.println("user  ----erglergknjsgdnkwjergfnksj");
                return jwtService.generateToken(users.getEmail());
            }
        } catch (BadCredentialsException e) {
            return "fail";
        }
    }

    public void assignRoleToUser(String email, String role) {

        Users user = userRepository.findByEmail(email);

        if (user == null) {
            user = new Users();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("defaultPassword")); // Or generate a password
            user.setRole(role);
            userRepository.save(user);
        } else {
            user.setRole(role);
            userRepository.save(user);
        }
    }
}
