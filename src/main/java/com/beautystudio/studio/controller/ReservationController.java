package com.beautystudio.studio.controller;

import com.beautystudio.studio.model.Category;
import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.SubCategory;
import com.beautystudio.studio.model.User;
import com.beautystudio.studio.service.ICategoryService;
import com.beautystudio.studio.service.IReservationService;
import com.beautystudio.studio.service.ISubCategoryService;
import com.beautystudio.studio.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISubCategoryService subCategoryService;

    @Autowired
    private IReservationService reservationService;

    @GetMapping(value = "/addReservation")
    public String showReservationForm( Model model){
        List<Category> categories = categoryService.findAll();
        List<SubCategory> subCategories = subCategoryService.findAll();
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("categoryList", categories);
        model.addAttribute("subcategoryList", subCategories);
        return "reservation/reservationForm";
    }

    @PostMapping(value = "/addReservation")
    public String addReservation(@ModelAttribute Reservation reservation, Principal principal){
        User user = getUser(principal);
        reservationService.save(reservation, user);
        return "redirect:/";
    }

    private User getUser(Principal principal) {
        return userService.findById(Long.parseLong(userService.findByEmail(principal.getName()).getId().toString()));
    }
}
