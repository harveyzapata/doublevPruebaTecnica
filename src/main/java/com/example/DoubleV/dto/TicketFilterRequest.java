package com.example.DoubleV.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketFilterRequest {
    private Long id;
    private Date updateDate;
    private Date creationDate;
    private String status;
    private String user;
    Pageable pageable;
}
