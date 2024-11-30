package com.example.projectrbac.service;

import com.example.projectrbac.model.Users;
import com.example.projectrbac.repository.userprincipal;
import com.example.projectrbac.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class customuserservice implements UserDetailsService {

    @Autowired
    userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(username);

        if(user == null)
            throw new UsernameNotFoundException("User Not Found");

        System.out.println("User found: " + user.getEmail());
        System.out.println("user Role: " + user.getRole());
        return new userprincipal(user);
    }

}
