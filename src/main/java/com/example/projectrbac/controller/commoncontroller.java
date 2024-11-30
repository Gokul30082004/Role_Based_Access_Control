package com.example.projectrbac.controller;

import com.example.projectrbac.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.projectrbac.repository.userRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class commoncontroller {

    @Autowired
    private userRepository userRepository;

    @GetMapping("/update_password")
    public String edit_profile(@AuthenticationPrincipal Users users, Model model){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserEmail = authentication.getName();

        model.addAttribute("email", currentUserEmail);
        System.out.println(currentUserEmail);
        return "edit_profile";
    }

    @PostMapping("/update_password")
    public String updatePassword(@RequestParam String currentPassword,
                                 @RequestParam String password,
                                 @RequestParam String confirmPassword,
                                 Model model) {

        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = userRepository.findByEmail(loggedInEmail);

        if (user != null) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(currentPassword, user.getPassword())) {

                if (password.equals(confirmPassword)) {
                    // Update the password
                    user.setPassword(encoder.encode(password));
                    userRepository.save(user); // Save the updated user

                    model.addAttribute("message", "Password updated successfully!");
                    return "redirect:/welcome"; // Redirect to the welcome page after successful update
                } else {
                    model.addAttribute("message", "New password and confirmation password do not match.");
                }
            } else {
                model.addAttribute("message", "Incorrect current password.");
            }
        } else {
            model.addAttribute("message", "User not found.");
        }

        return "update_password";
    }

    @GetMapping("/view_details")
    public String viewDetails(Model model) {

        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = userRepository.findByEmail(loggedInEmail);

        if (user != null) {
            model.addAttribute("email", user.getEmail());
            model.addAttribute("role", user.getRole());
        } else {
            model.addAttribute("message", "User not found.");
        }

        return "view_details";
    }
}
