package com.project.lifebank.controller;

import com.project.lifebank.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonorController {

    private final EventService eventService;

    public DonorController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/donor")
    public String getDonorDash( Model model){
        model.addAttribute("event", eventService.getAllEvents());
        return "donor_dash";
    }
}
