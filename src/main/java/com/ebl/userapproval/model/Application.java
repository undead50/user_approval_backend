package com.ebl.userapproval.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private Long id;

    private String applicationName;

    private String createdBy;
}
