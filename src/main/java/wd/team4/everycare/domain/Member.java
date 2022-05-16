package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.member.JobOfferMemberDTO;
import wd.team4.everycare.dto.member.MemberAccountDTO;
import wd.team4.everycare.dto.member.MemberInfoDTO;
import wd.team4.everycare.dto.member.MemberListViewDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "member_account_number")
    private String accountNumber;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private CareSitter careSitter;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Store store;

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

    public MemberListViewDTO toMemberListViewDTO(){
        return MemberListViewDTO.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .gender(this.gender)
                .birth(this.birth)
                .phone(this.phone)
                .email(this.email)
                .createdAt(this.createdAt)
                .activityStatus(this.activityStatus)
                .build();
    }

    public JobOfferMemberDTO toJobOfferMemberDTO(){
        return JobOfferMemberDTO.builder()
                .name(this.name)
                .address(this.address)
                .birth(this.birth)
                .gender(String.valueOf(this.gender))
                .email(this.email)
                .phone(this.phone)
                .build();
  }
  public void updateAccount(MemberAccountDTO memberAccountDTO) {
        if(StringUtils.isNotBlank(memberAccountDTO.getId())) {
            this.id = memberAccountDTO.getId();
        }
        if(StringUtils.isNotBlank(memberAccountDTO.getName())) {
            this.name = memberAccountDTO.getName();
        }
        if(StringUtils.isNotBlank(String.valueOf(memberAccountDTO.getGender()))) {
            this.gender = memberAccountDTO.getGender();
        }
        if(StringUtils.isNotBlank(String.valueOf(memberAccountDTO.getBirth()))) {
            this.birth = memberAccountDTO.getBirth();
        }
        if(StringUtils.isNotBlank(memberAccountDTO.getPhone())) {
            this.phone = memberAccountDTO.getPhone();
        }
        if(StringUtils.isNotBlank(memberAccountDTO.getEmail())) {
            this.email = memberAccountDTO.getEmail();
        }
        if(StringUtils.isNotBlank(memberAccountDTO.getAccountNumber())) {
            this.zipcode = memberAccountDTO.getZipcode();
        }
        if(StringUtils.isNotBlank(memberAccountDTO.getAddress())) {
            this.address = memberAccountDTO.getAddress();
        }
        if(StringUtils.isNotBlank(memberAccountDTO.getBank())) {
            this.bank = memberAccountDTO.getBank();
        }
        if(StringUtils.isNotBlank(memberAccountDTO.getAccountNumber())) {
            this.accountNumber = memberAccountDTO.getAccountNumber();
        }
    }
}
