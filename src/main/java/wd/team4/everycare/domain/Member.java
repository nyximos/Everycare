package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.member.MemberInfoDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Member {

    @Id
    @Column(name ="member_id", length = 20, nullable = false)
    private String id;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Column(name = "member_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender")
    private Gender gender;

    @Column(name = "member_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
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

    @Column(name = "member_detailed_address")
    private String detailedAddress;

    @Column(name = "member_admin_registration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime adminRegistrationDate;

    @Column(name = "member_bank", length = 45)
    private String bank;

    @Column(name = "member_account_member")
    private String accountNumber;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private CareSitter careSitter;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Store store;

    @OneToMany(mappedBy = "member")
    private List<CareTarget> careTargets = new ArrayList<>();

    @Builder
    public Member(String id, String password, String name, MemberRole role, Gender gender, LocalDate birth, String phone, String email, LocalDateTime createdAt, ActivityStatus activityStatus, String zipcode, String address, String detailedAddress, String bank, String accountNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.activityStatus = activityStatus;
        this.zipcode = zipcode;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    public void encodePassword(String password){
       this.password = password;
    }

    public void registrationAdmin(LocalDateTime time){
        this.role = MemberRole.ROLE_ADMIN;
        this.adminRegistrationDate = time;
    }

    public MemberInfoDTO toMemberInfoDTO() {
        return MemberInfoDTO.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .build();
    }
}
