package com.project.lifebank.controller;

import com.project.lifebank.dto.DonationDto;
import com.project.lifebank.service.DonationService;
import com.project.lifebank.service.EventService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoctorController {
    private final DonationService donationService;
    private final EventService eventService;

    public DoctorController(DonationService donationService, EventService eventService) {
        this.donationService = donationService;
        this.eventService = eventService;
    }

    String email;
    @GetMapping("/doctor")
    public String getDoctorDash(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails)authentication.getPrincipal();
        email = user.getUsername();
        List<DonationDto> donations = donationService.getAllDonationByDoctor(email);
        if(!donations.isEmpty()){
            model.addAttribute("donations", donations);
            model.addAttribute("names", email);
        }else{
            model.addAttribute("Notification", "You've not yet recorded donations at an event");
        }
        return "doc_dash";

    }

    @GetMapping("/doctor/add-donation")
    public String getDonationRecordForm(Model model){
        DonationDto donationDto = new DonationDto();
        model.addAttribute("donation",donationDto);
        return "record-donation";
    }

    @PostMapping("/doctor/record-donation")
    public String recordDonation(@ModelAttribute("donation")DonationDto donationDto){
        donationService.saveDonation(donationDto);
        return "redirect:/doctor/donations";
    }

//    @GetMapping("/doctor/donations")
//    public String getDonations(Model model){
//        model.addAttribute("donations", donationService.getAllDonation());
//        return "donations";
//    }

    @GetMapping("/doctor/update-donation/{id}")
    public String updateDonationForm(@PathVariable("id")int id, Model model){
        model.addAttribute("donation", donationService.getDonationById(id).orElse(null));
        return "update-donation";
    }

    @PostMapping("/doctor/update")
    public String updateDonation(@ModelAttribute DonationDto donationDto){
        donationService.updateDonation(donationDto);
        return "redirect:/donations";
    }

    @GetMapping("/doctor/delete-donation/{id}")
    public String deleteDonation(@PathVariable("id") int id){
        donationService.deleteDonationById(id);
        return "redirect:/doctor/donations";
    }

    @GetMapping("/doctor/donations")
    public String getDoctorDonationsPage(Model model){
        model.addAttribute("donations", donationService.getAllDonationByDoctor(email));
        return "donations";
    }

    @GetMapping("/doctor/events")
    public String showEvents(Model model){
        model.addAttribute("eventDoc", eventService.getAllEvents());
        return "doc-event";
    }
}
