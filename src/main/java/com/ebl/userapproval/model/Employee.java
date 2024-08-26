package com.ebl.userapproval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.mapping.Join;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String domainName;

    private String role;

    private String departmentName;

    private String isActive;

    private String isCbsUser;

    private String pwd;

    private String cbsUsername;

    private String systemRole;

    private String employeeDesignation;

    private String branch;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="approval_master_id")
    UserApprovalMaster userApprovalMaster;


    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

}