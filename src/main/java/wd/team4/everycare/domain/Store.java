package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
}
