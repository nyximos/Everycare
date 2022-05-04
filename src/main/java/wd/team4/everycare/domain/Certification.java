package wd.team4.everycare.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.CertificationViewDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "certification_seq_generator",
        sequenceName = "certification_seq",
        initialValue = 1, allocationSize = 1)
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certification_seq_generator")
    @Column(name = "certification_id")
    private Long id;

    @Column(name = "certification_name")
    private String name;

    @Column(name = "certification_upload_file_name", nullable = false)
    private String uploadFileName;

    @Column(name = "certification_store_file_name", nullable = false)
    private String storeFileName;


    @Column(name = "certification_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_classification_id")
    private CertificationClassification certificationClassification;

    @Column(name = "certification_admin_approve", nullable = false)
    private int adminApproval;

    @Column(name = "certification_approval_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime approvalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_approver")
    private Member approver;

    @Builder
    public Certification(String name, String uploadFileName, String storeFileName, int adminApproval, LocalDateTime createdAt, CareSitter careSitter, CertificationClassification certificationClassification) {
        this.name = name;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.adminApproval = adminApproval;
        this.createdAt = createdAt;
        this.careSitter = careSitter;
        this.certificationClassification = certificationClassification;
    }

    public CertificationViewDTO toViewDTO(){
        return CertificationViewDTO.builder()
                .id(this.id)
                .name(this.name)
                .adminApproval(this.adminApproval)
                .uploadFileName(this.uploadFileName)
                .storeFileName(this.storeFileName)
                .createdAt(this.createdAt)
                .careSitter(this.careSitter.toNameDTO())
                .build();
    }

    public void approvedByAdmin(int i, LocalDateTime date, Member approver){
        this.adminApproval = i;
        this.approvalDate = date;
        this.approver = approver;
    }

}