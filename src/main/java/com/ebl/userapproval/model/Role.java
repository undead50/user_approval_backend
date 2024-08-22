package com.ebl.userapproval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role_table")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private Long id;

    private Long applicaitonId;

    private String role;

    private String createdBy;
}
