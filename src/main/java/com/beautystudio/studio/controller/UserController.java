package com.beautystudio.studio.controller;

import com.beautystudio.studio.config.JavaSenderMail;
import com.beautystudio.studio.model.User;
import com.beautystudio.studio.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private JavaSenderMail javaSenderMail;

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
            model.addAttribute("messageerror", "Nieprawidłowy e-mail lub hasło");
        }
        if (logout != null){
            model.addAttribute("messagelogout", "Wylogowano pomyślnie");
        }
        return "login";
    }

    @GetMapping(value = "/register")
    public String showFormRegistration(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String registrationUser(@ModelAttribute User user, @RequestParam String pemail, @RequestParam String pass, BindingResult result, Model model){
        User u = iUserService.findByEmail(user.getEmail());
        if (u != null){
            model.addAttribute("email", "Ten email jest zarejestrowany");
            return "register";
        } else if (!pemail.equals(user.getEmail())){
            model.addAttribute("pemailError", "Email nie pasują");
            return "register";
        } else if (!pass.equals(user.getPassword())){
            model.addAttribute("passError", "Hasła nie pasują");
            return "register";
        } else if (result.hasErrors()){
            return "register";
        } else {
            iUserService.saveUser(user);
            return "redirect:/login";
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

    @GetMapping(value = "/forgot")
    public String showForgotPasswordForm(){
        return "emailCheckForm";
    }

    @PostMapping(value = "/forgot")
    public String processForgotPasswordForm(@RequestParam("email") String userEmail, HttpServletRequest request, Model model) {
        User user = iUserService.findByEmail(userEmail);
        if (user == null) {
            model.addAttribute("errorMessage", "Nie znaleźliśmy konta dla tego adresu e-mail.");
        } else {
            user.setResetToken(UUID.randomUUID().toString());
            iUserService.saveToken(user);
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            javaSenderMail.sendEmailWithResetToken(user, appUrl);
            model.addAttribute("successMessage", "Link do resetowania hasła został wysłany na adres " + userEmail);
        }
        return "emailCheckForm";
    }

    @GetMapping(value = "/reset")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {

        User user = iUserService.findResetToken(token);
        if (user != null) {
            model.addAttribute("resetToken", token);
        } else {
            model.addAttribute("errorMessage", "Oops!  To jest nieprawidłowy link do resetowania hasła..");
        }
        return "forgotPasswordForm";
    }

    @PostMapping(value = "/reset")
    public String setNewPassword(Model model, @RequestParam Map<String, String> requestParams) {

        User user = iUserService.findResetToken(requestParams.get("token"));
        if (user != null){
            iUserService.forgetPassword(user,requestParams);
            return "redirect:/login";
        }  else {
            model.addAttribute("errorLink", "Oops!  To jest nieprawidłowy link do resetowania hasła..");
            return "forgotPasswordForm";
        }
    }
}
