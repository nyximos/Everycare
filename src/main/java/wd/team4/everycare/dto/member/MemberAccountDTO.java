package wd.team4.everycare.dto.member;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberAccountDTO {

    private String id;
    private String name;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String phone;
    @Email(message = "이메일 형식이 아니에요")
    @NotBlank(message = "이메일이 입력되지 않았어요")
    private String email;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private String bank;
    private String accountNumber;

}
