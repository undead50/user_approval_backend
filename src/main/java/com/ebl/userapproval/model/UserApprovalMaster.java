package com.ebl.userapproval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;



@Getter
@Setter
@Entity
@Table(name = "user_approval_master")
public class UserApprovalMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "access_type")
    private String accessType;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    private String status;

    private Integer requestedBy;

    private Integer currentHandler;

    private Integer recommendedBy;

    private Integer approvedBy;

    @Column(name = "cbs_user_name") // Add or update this annotation to map correctly
    private String cbsUserName;

    private String delFlag;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Add a collection for ApplicationRoleRequests
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="approval_master_id",referencedColumnName = "id")
    private List<ApplicationRoleRequest> applicationRoleRequests;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="approval_master_id",referencedColumnName = "id")
    private List<UserApprovalHistory> userApprovalHistories;
}
