package com.ebl.userapproval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "request_table")
public class Request {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private Long id;

    private Long applicaitonId;

    private String requestType;

    private String createdBy;
}
