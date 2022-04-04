package wd.team4.everycare.dto.member;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.MemberRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter @Setter
@ToString
public class SignupDTO {

    @NotBlank(message = "아이디가 입력되지 않았어요")
    private String id;
    @NotBlank(message = "비밀번호가 입력되지 않았어요")
    private String password;
    private String name;
    private MemberRole role;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String phone;
    @Email(message = "이메일 형식이 아니에요")
    @NotBlank(message = "이메일이 입력되지 않았어요")
    private String email;
    private LocalDateTime createdAt;
    private ActivityStatus activityStatus;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private LocalDateTime adminRegistrationDate;
    private String bank;
    private String accountNumber;

    @Builder
    public SignupDTO(String id, String password, String name, MemberRole role, Gender gender, LocalDate birth, String phone, String email, LocalDateTime createdAt, ActivityStatus activityStatus, String zipcode, String address, String detailedAddress, LocalDateTime adminRegistrationDate, String bank, String accountNumber) {
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
        this.adminRegistrationDate = adminRegistrationDate;
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    public void encodePassword(String password){
        this.password = password;
    }

}
