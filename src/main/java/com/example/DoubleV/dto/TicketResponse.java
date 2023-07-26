package com.example.DoubleV.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {

    private Long id;
    private String user;
    private Date updateDate;
    private Date creationDate;
    private String status;
}
