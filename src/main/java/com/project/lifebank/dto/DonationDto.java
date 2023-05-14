package com.project.lifebank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DonationDto {
    private int id;
    private String eventName;
    private String donorEmail;
    private double quantity;
    private String bloodType;
    private String doctorEmail;
    private LocalDate donationDate;
}
