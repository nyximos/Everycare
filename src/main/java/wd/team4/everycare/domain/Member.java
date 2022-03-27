package wd.team4.everycare.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @Column(name ="member_id", length = 20, nullable = false)
    private String id;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Column(name = "member_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender")
    private Gender gender;

    @Column(name = "member_birth")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate birth;

    @Column(name = "member_phone", length = 45)
    private String phone;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_activity_status")
    private ActivityStatus activityStatus;

    @Column(name = "member_zipcode", length = 45)
    private String zipcode;

    @Column(name = "member_address")
    private String address;

    @Column(name = "member_address_detail")
    private String addressDetail;

    @Column(name = "member_role", length = 5)
    private String role;

    @Column(name = "member_admin_registration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime adminRegistrationDate;

    @Column(name = "member_bank", length = 45)
    private String bank;

    @Column(name = "member_account_member")
    private String accountNumber;

    @Builder
    public Member(String id, String password, String name, Gender gender, LocalDate birth, String phone, String email, LocalDateTime createdAt, ActivityStatus activityStatus, String zipcode, String address, String addressDetail, String role, LocalDateTime adminRegistrationDate, String bank, String accountNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.activityStatus = activityStatus;
        this.zipcode = zipcode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.role = role;
        this.adminRegistrationDate = adminRegistrationDate;
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    public void changeMemberRole(String role) {
        this.role = role;
    }

    public void encodePassword(String password){
       this.password = password;
    }

    // ENUM으로 안하고 ,로 해서 구분해서 ROLE을 입력 -> 그걸 파싱!!
    public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }
}
