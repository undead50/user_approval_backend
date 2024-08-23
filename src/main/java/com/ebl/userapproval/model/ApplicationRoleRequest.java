package com.ebl.userapproval.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "application_role_requests")
public class ApplicationRoleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int applicationId;

    private int roleTypeId;

    private String serviceType;

    private int requestType;

}
