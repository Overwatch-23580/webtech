package com.project.lifebank.repository;

import com.project.lifebank.dto.DonationDto;
import com.project.lifebank.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Integer> {
    List<Donation> getDonationsByEventName(String eventName);
    List<Donation> getDonationsByDonorEmail(String email);
    List<Donation> getDonationsByDoctorEmail(String email);
}
