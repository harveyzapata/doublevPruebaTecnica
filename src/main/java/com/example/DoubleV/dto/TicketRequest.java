package com.example.DoubleV.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {

    private Date updateDate;
    private Date creationDate;
    private String status;
    private String user;
}


