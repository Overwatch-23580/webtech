package com.project.lifebank.service;

import com.project.lifebank.dto.DonationDto;
import com.project.lifebank.model.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationService{
    void saveDonation(DonationDto donationDto);
    Optional<Donation> getDonationById(int id);
    void deleteDonationById(int id);
    void updateDonation(DonationDto donationDto);
    List<DonationDto> getAllDonation();
    List<DonationDto> getAllDonationByEvent(String eventName);
    List<DonationDto> getAllDonationByDonor(String email);
    List<DonationDto> getAllDonationByDoctor(String email);
}
