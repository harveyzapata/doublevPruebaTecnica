package com.example.DoubleV.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id")
    private Long id;
    
    @Column(name = "users")
    private String user;

    @Column(name = "creationdate")
    private Date creationDate;
    
    @Column(name = "updatedate")
    private Date updateDate;
    
    @Column(name = "status")
    private String status;
}
