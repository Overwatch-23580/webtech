package com.project.lifebank.controller;

import com.project.lifebank.dto.EventDto;
import com.project.lifebank.dto.RoleDto;
import com.project.lifebank.dto.UserDto;
import com.project.lifebank.model.User;
import com.project.lifebank.service.DonationService;
import com.project.lifebank.service.EventService;
import com.project.lifebank.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AdminController {
    private final EventService eventService;
    private final UserService userService;
    private final DonationService donationService;

    public AdminController(EventService eventService, UserService userService, DonationService donationService) {
        this.eventService = eventService;
        this.userService = userService;
        this.donationService = donationService;
    }


    @GetMapping("/admin")
    public String getAdminDashboard(Model model){
        model.addAttribute("events", eventService.getAllEvents());
        return "admin_dash";
    }

    @GetMapping("/admin/create-event")
    public String getCreateEventForm(Model model){
        EventDto event = new EventDto();
        model.addAttribute("event", event);
        return "create-event";
    }

    @PostMapping("/admin/create-event")
    public String createEvent(@ModelAttribute("event") EventDto eventDto){
        eventService.saveEvent(eventDto);
        return "redirect:/admin/event";
    }


    @GetMapping("/admin/event")
    public String getEventPage(Model model){
        model.addAttribute("events",eventService.getAllEvents());
        return "admin-event";
    }



    @GetMapping("/admin/edit-event/{id}")
    public String getEditForm(@PathVariable("id") int id, Model model){
        model.addAttribute("event", eventService.getEventById(id).orElse(null));
        return "edit-event";
    }

    @PostMapping("/admin/edit-event")
    public String edit(@ModelAttribute EventDto eventDto){
        eventService.updateEvent(eventDto);
        return "redirect:/admin/event";
    }


    @GetMapping("/admin/delete-event/{id}")
    public String deleteEvent(@PathVariable("id")int id){
        eventService.deleteEventById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/admin/users")
    public String getUserPage(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "admin-users";
    }

    @GetMapping("/admin/donations")
    public String getDonationsPage(Model model){
        model.addAttribute("donations", donationService.getAllDonation());
        return "admin-donations";
    }
}
