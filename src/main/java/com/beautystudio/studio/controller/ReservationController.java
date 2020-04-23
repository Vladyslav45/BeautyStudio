package com.beautystudio.studio.controller;

import com.beautystudio.studio.model.Category;
import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.User;
import com.beautystudio.studio.service.ICategoryService;
import com.beautystudio.studio.service.IReservationService;
import com.beautystudio.studio.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private IReservationService reservationService;

    @GetMapping(value = "/addReservation")
    public String showReservationForm( Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("categoryList", categories);
        return "reservation/reservationForm";
    }

    @PostMapping(value = "/addReservation")
    public String addReservation(@ModelAttribute Reservation reservation, Principal principal){
        User user = getUser(principal);
        reservationService.save(reservation, user);
        return "redirect:/";
    }


    @GetMapping(value = "/userReservation")
    public String showUserReservationsForm(Principal principal, Model model){
        User user = getUser(principal);
        List<Reservation> reservations = reservationService.showAllUserReservation(user.getId());
        model.addAttribute("userReservations", reservations);
        return "reservation/userReservationForm";
    }

    @GetMapping(value = "/userReservation/{reservationId}")
    public String rejectReservation(@PathVariable("reservationId") Long reservationId){
        reservationService.deleteReservation(reservationId);
        return "redirect:/reservation/userReservation";
    }

    @GetMapping(value = "/editReservation/{id}")
    public String showEditReservationForm(@PathVariable("id") Long reservationId, Model model){
        Reservation reservation = reservationService.findById(reservationId);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoryList", categories);
        model.addAttribute("editReservation", reservation);
        return "reservation/editReservationForm";
    }

    @PostMapping(value = "/editReservation/{id}")
    public String editReservation(@ModelAttribute Reservation reservation){
        reservationService.update(reservation);
        return "redirect:/reservation/userReservation";
    }


    private User getUser(Principal principal) {
        return userService.findById(Long.parseLong(userService.findByEmail(principal.getName()).getId().toString()));
    }
}
