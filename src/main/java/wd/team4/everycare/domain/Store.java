package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.StoreAdminViewDTO;
import wd.team4.everycare.dto.StoreFormDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "store_seq_generator",
        sequenceName = "store_seq",
        initialValue = 1, allocationSize = 1)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_seq_generator")
    @Column(name = "store_id", nullable = false)
    private Long id;

    @Column(name = "store_name", length = 50, nullable = false)
    private String name;

    @Column(name = "store_url", nullable = false)
    private String url;

    @Column(name = "store_business_license_number", length = 10, nullable = false)
    private int businessLicenseNumber;

    @Column(name = "store_email", nullable = false)
    private String email;

    @Column(name = "store_operation_start_time", length = 5, nullable = false)
    private String operationStartTime;

    @Column(name = "store_operation_end_time", length = 5, nullable = false)
    private String operationEndTime;

    @Column(name = "store_lunch_start_time", length = 5, nullable = false)
    private String lunchStartTime;

    @Column(name = "store_lunch_end_time", length = 5, nullable = false)
    private String lunchEndTime;

    @Column(name = "store_closed_day", length = 30, nullable = false)
    private String closedDay;

    @Column(name = "store_company_corporation_name", nullable = false)
    private String companyCorporationName;

    @Column(name = "store_representative_name", length = 30, nullable = false)
    private String representativeName;

    @Column(name = "store_business_location", nullable = false)
    private String businessLocation;

    @Column(name = "store_customer_service_number", length = 45, nullable = false)
    private String customerServiceNumber;

    @Column(name = "store_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "store_admin_approval", nullable = false )
    private int adminApproval;

    @Column(name = "store_approval_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime approvalDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Store(Long id, String name, String url, int businessLicenseNumber, String email, String operationStartTime, String operationEndTime, String lunchStartTime, String lunchEndTime, String closedDay, String companyCorporationName, String representativeName, String businessLocation, String customerServiceNumber, Member member) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.businessLicenseNumber = businessLicenseNumber;
        this.email = email;
        this.operationStartTime = operationStartTime;
        this.operationEndTime = operationEndTime;
        this.lunchStartTime = lunchStartTime;
        this.lunchEndTime = lunchEndTime;
        this.closedDay = closedDay;
        this.companyCorporationName = companyCorporationName;
        this.representativeName = representativeName;
        this.businessLocation = businessLocation;
        this.customerServiceNumber = customerServiceNumber;
        this.member = member;
    }

    public void updateInfo(StoreFormDTO storeFormDTO){
        if (StringUtils.isNotBlank(storeFormDTO.getName()))
            this.name = storeFormDTO.getName();
        if (StringUtils.isNotBlank(storeFormDTO.getUrl()))
            this.url = storeFormDTO.getUrl();
        if (StringUtils.isNotBlank(String.valueOf(storeFormDTO.getBusinessLicenseNumber())))
            this.businessLicenseNumber = storeFormDTO.getBusinessLicenseNumber();
        if (StringUtils.isNotBlank(storeFormDTO.getEmail()))
            this.email = storeFormDTO.getEmail();
        if (StringUtils.isNotBlank(storeFormDTO.getOperationStartTime()))
            this.operationStartTime = storeFormDTO.getOperationStartTime();
        if (StringUtils.isNotBlank(storeFormDTO.getOperationEndTime()))
            this.operationEndTime = storeFormDTO.getOperationEndTime();
        if (StringUtils.isNotBlank(storeFormDTO.getLunchStartTime()))
            this.lunchStartTime = storeFormDTO.getLunchStartTime();
        if (StringUtils.isNotBlank(storeFormDTO.getLunchEndTime()))
            this.lunchEndTime = storeFormDTO.getLunchEndTime();
        if (StringUtils.isNotBlank(storeFormDTO.getClosedDay()))
            this.closedDay = storeFormDTO.getClosedDay();
        if (StringUtils.isNotBlank(storeFormDTO.getCompanyCorporationName()))
            this.companyCorporationName = storeFormDTO.getCompanyCorporationName();
        if (StringUtils.isNotBlank(storeFormDTO.getRepresentativeName()))
            this.representativeName = storeFormDTO.getRepresentativeName();
        if (StringUtils.isNotBlank(storeFormDTO.getBusinessLocation()))
            this.businessLocation = storeFormDTO.getBusinessLocation();
        if (StringUtils.isNotBlank(storeFormDTO.getCustomerServiceNumber()))
            this.customerServiceNumber = storeFormDTO.getCustomerServiceNumber();
    }

    public void approvedByAdmin (int approval, LocalDateTime date){
        this.adminApproval = approval;
        this.approvalDate = date;
    }

    public void saveTime(LocalDateTime time) {
        this.createdAt = time;
    }

    public StoreFormDTO toFormDTO() {
        return StoreFormDTO.builder()
                .name(this.name)
                .url(this.url)
                .businessLicenseNumber(this.businessLicenseNumber)
                .email(this.email)
                .operationStartTime(this.operationStartTime)
                .operationEndTime(this.operationEndTime)
                .lunchStartTime(this.lunchStartTime)
                .lunchEndTime(this.lunchEndTime)
                .closedDay(this.closedDay)
                .companyCorporationName(this.companyCorporationName)
                .representativeName(this.representativeName)
                .businessLocation(this.businessLocation)
                .customerServiceNumber(this.customerServiceNumber)
                .build();
    }

    public StoreAdminViewDTO toAdminViewDTO() {
        return StoreAdminViewDTO.builder()
                .id(this.id)
                .name(this.name)
                .url(this.url)
                .businessLicenseNumber(this.businessLicenseNumber)
                .email(this.email)
                .operationStartTime(this.operationStartTime)
                .operationEndTime(this.operationEndTime)
                .lunchStartTime(this.lunchStartTime)
                .lunchEndTime(this.lunchEndTime)
                .closedDay(this.closedDay)
                .companyCorporationName(this.companyCorporationName)
                .representativeName(this.representativeName)
                .businessLocation(this.businessLocation)
                .customerServiceNumber(this.customerServiceNumber)
                .createdAt(this.createdAt)
                .member(this.member)
                .build();
    }


}
