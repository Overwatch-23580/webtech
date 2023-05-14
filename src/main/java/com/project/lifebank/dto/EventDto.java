package com.project.lifebank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
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
