package wd.team4.everycare.dto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordDTO {

    private String password;

    @NotBlank(message = "비밀번호가 입력되지 않았어요")
    private String newPassword;

}
