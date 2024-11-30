package com.example.projectrbac.controller;

import com.example.projectrbac.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.projectrbac.repository.userRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private userRepository userRepository;

    @GetMapping("/list_user")
    public String list_user(Model model) {

        List<Users> usersList = userRepository.findAll();

        model.addAttribute("users", usersList);
        usersList.forEach(user -> System.out.println("User: " + user.getEmail() + ", Role: " + user.getRole()));
        return "list_user";
    }

    @GetMapping("/delete_user")
    public String delete_user(@RequestParam(required = false) String email, Model model) {

        if (email.isEmpty())
            return "delete_user";

        Users user = userRepository.findByEmail(email);

        if (user == null){
            model.addAttribute("message", "User created successfully!");
            return "delete_user";
        }
        userRepository.delete(user);
        return "redirect:/welcome";
    }
    @GetMapping("/create_user")
    public String Create_User(Model model){
        model.addAttribute("user", new Users());
        System.out.println("asdfssdsaafsdsdfaasdf");
        return "create_user";
    }

    @PostMapping("/create_user")
    public String createUser(@Validated @ModelAttribute("user") Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_user";
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userRepository.save(user);

        model.addAttribute("message", "User created successfully!");
        return "redirect:/welcome";
    }


    @GetMapping("/update_user")
    public String showUpdateUserForm() {
        return "update_user";
    }

    @PostMapping("/update_user")
    public String searchUserByEmail(@RequestParam("email") String email, Model model) {
        Users user = userRepository.findByEmail(email);

        if (user != null) {
            model.addAttribute("user", user);
            return "update_user";
        } else {
            model.addAttribute("message", "User with email " + email + " not found.");
            return "update_user";
        }
    }



    @PostMapping("/save_user")
    public String saveUpdatedUser(@Validated @ModelAttribute("user") Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update_user";
        }

        userRepository.save(user);
        model.addAttribute("message", "User details updated successfully!");
        return "redirect:/welcome";
    }

}
