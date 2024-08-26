package com.ebl.userapproval.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private Long id;

    private String applicationName;

    // Add a collection for ApplicationRoleRequests
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="application_id",referencedColumnName = "id")
    private List<ApplicationOwner> applicationOwnerList;

    private String createdBy;
}
