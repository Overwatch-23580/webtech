package com.project.lifebank.service;

import com.project.lifebank.dto.DonationDto;
import com.project.lifebank.model.Donation;
import com.project.lifebank.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonationServiceImpl implements DonationService{
    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public void saveDonation(DonationDto donationDto) {
        Donation donation = mapToDonation(donationDto);
        donationRepository.save(donation);
    }

    @Override
    public Optional<Donation> getDonationById(int id) {
        return donationRepository.findById(id);
    }

    @Override
    public void deleteDonationById(int id) {
        donationRepository.deleteById(id);
    }

    @Override
    public void updateDonation(DonationDto donationDto) {
        Optional<Donation> existingDonation = donationRepository.findById(donationDto.getId());
        if(existingDonation.isPresent()){
            Donation updatedDonation  = mapToDonation(donationDto);
            donationRepository.delete(existingDonation.get());
            donationRepository.save(updatedDonation);
        }else{
            System.out.println("You don't exist");
        }
    }

    @Override
    public List<DonationDto> getAllDonation() {
        List<Donation> donations = donationRepository.findAll();
        return donations.stream()
                .map(this::mapToDonationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DonationDto> getAllDonationByEvent(String eventName) {
        List<Donation> donations = donationRepository.getDonationsByEventName(eventName);
        return donations.stream()
                .map(this::mapToDonationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DonationDto> getAllDonationByDonor(String email) {
        List<Donation> donations = donationRepository.getDonationsByDonorEmail(email);
        return donations.stream()
                .map(this::mapToDonationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DonationDto> getAllDonationByDoctor(String email) {
        List<Donation> donations = donationRepository.getDonationsByDoctorEmail(email);
        return donations.stream()
                .map(this::mapToDonationDto)
                .collect(Collectors.toList());
    }


    private Donation mapToDonation(DonationDto donationDto){
        Donation donation = new Donation();
        donation.setId(donationDto.getId());
        donation.setEventName(donationDto.getEventName());
        donation.setQuantity(donationDto.getQuantity());
        donation.setBloodType(donationDto.getBloodType());
        donation.setDonorEmail(donationDto.getDonorEmail());
        donation.setDoctorEmail(donation.getDonorEmail());
        donation.setDonationDate(donationDto.getDonationDate());
        return donation;
    }

    private DonationDto mapToDonationDto(Donation d) {
        DonationDto donationDto = new DonationDto();
        donationDto.setId(d.getId());
        donationDto.setDonorEmail(d.getDonorEmail());
        donationDto.setQuantity(d.getQuantity());
        donationDto.setDoctorEmail(d.getDoctorEmail());
        donationDto.setBloodType(d.getBloodType());
        donationDto.setEventName(d.getEventName());
        donationDto.setDonationDate(d.getDonationDate());
        return donationDto;
    }

}
