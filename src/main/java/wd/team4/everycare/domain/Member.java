package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@SequenceGenerator( name = "member_seq_generator",
        sequenceName = "member_seq",
        initialValue = 1, allocationSize = 1)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    @Column(name ="member_id", length = 20, nullable = false)
    private String id;

    @Column(name = "member_password", length = 255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender", nullable = false)
    private Gender gender;

    @Column(name = "member_birth", nullable = false)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate birth;

    @Column(name = "member_phone", length = 45, nullable = false)
    private String phone;

    @Column(name = "member_email", length = 255, nullable = false)
    private String email;

    @Column(name = "member_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_status", nullable = false)
    private String activityStatus;

    @Column(name = "member_zipcode", length = 45, nullable = false)
    private String zipcode;

    @Column(name = "member_address", length = 255, nullable = false)
    private String address;

    @Column(name = "member_address_detail", length = 255, nullable = false)
    private String addressDetail;

    @Column(name = "member_admin_registration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime adminRegistrationDate;

    @Column(name = "member_bank", length = 45)
    private String bank;

    @Column(name = "member_account_member", length = 255)
    private String accountNumber;


}
