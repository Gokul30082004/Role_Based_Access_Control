package com.example.projectrbac.controller;


import com.example.projectrbac.model.Users;
import com.example.projectrbac.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.projectrbac.repository.userRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Controller
public class authenticationController {

    @Autowired
    private userRepository userRepository;
    @Autowired
    private userservice userservice;
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout-success")
    public String logout_success(){
        return "logout";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PostMapping("/login")
    public String log(@RequestParam Users users){

        System.out.println("inside post login welcome");
        return userservice.isvaliduser(users);
    }

    @GetMapping("/user_detail")
    public String user_detail(){
        return "user_detail";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new Users());
        return "signup";
    }
    @PostMapping("/signup")
    public String processSignup(@Validated @ModelAttribute("user") Users user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "signup";
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
        model.addAttribute("message", "Email already in use. Please try a different one.");
        return "signup";
    }

    String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        model.addAttribute("message", "Signup successful! You can now log in.");
        return "redirect:/login";
    }


    @GetMapping("/role_selection")
    public String showRoleSelectionPage(@AuthenticationPrincipal OAuth2User principal, Model model) {

        String email = principal.getAttribute("email");
        model.addAttribute("email", email);
        if(userRepository.findByEmail(email) != null)
            return "welcome";
        return "role_selection";  // This will be the JSP page for selecting a role
    }

    @PostMapping("/save_role")
    public String saveRole(@AuthenticationPrincipal OAuth2User principal, @RequestParam("role") String role) {

        String email = principal.getAttribute("email");

        userservice.assignRoleToUser(email, role);

        return "redirect:/welcome";  // Redirect to the home page or profile page
    }
}
