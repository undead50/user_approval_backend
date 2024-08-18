package com.ebl.userapproval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "blacklisted_token")
public class Blacklisttoken {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private Long id;
    private String token;
    @CreationTimestamp
    private LocalDate createdAt;
}
