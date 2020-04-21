package com.beautystudio.studio.controller;

import com.beautystudio.studio.model.User;
import com.beautystudio.studio.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping(value = "/")
    public String showRolePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = iUserService.findByEmail(authentication.getName());
        if (user == null || user.getRole().getRoleType().equals("ROLE_USER")){
            model.addAttribute("user", user);
            return "index";
        } else if (user.getRole().getRoleType().equals("ROLE_ADMIN")){
            return "redirect:/admin/main";
        }
        return null;
    }

    @GetMapping(value = {"/login"})
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model){
        if (error != null){
            model.addAttribute("messageerror", "Invalid email or password");
        }
        if (logout != null){
            model.addAttribute("messagelogout", "You've been logged out successfully");
        }
        return "login";
    }

    @GetMapping(value = "/register")
    public String showFormRegistration(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String registrationUser(@ModelAttribute User user, @RequestParam String pemail, @RequestParam String pass){
        if (pemail.equals(user.getEmail()) && pass.equals(user.getPassword())) {
            iUserService.saveUser(user);
            return "redirect:/login";
        } else {
            return "register";
        }
    }

    @GetMapping(value = "/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long userId, Model model){
        User user = iUserService.findById(userId);
        model.addAttribute("user", user);
        return "updateForm";
    }

    @PostMapping(value = "/update/{id}")
    public String updateUser(@ModelAttribute User user){
        iUserService.update(user);
        return "redirect:/";
    }
}
