package com.project.lifebank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ownerEmail;
    private String title;
    private String description;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private String location;
    private String startTime;
    private String endingTime;
    private byte[] eventImage;
}
