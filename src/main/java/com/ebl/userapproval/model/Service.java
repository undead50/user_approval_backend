package com.ebl.userapproval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "service_table")
public class Service {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long roleId;

    private Long applicationId;

    private String service;

    private String service_code;

    private String createdBy;
}
