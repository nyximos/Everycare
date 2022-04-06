package wd.team4.everycare.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
@Getter @Setter
public class MemberDTO {

    @NotBlank(message = "아이디가 입력되지 않았어요")
    private String id;

    @NotBlank(message = "비밀번호가 입력되지 않았어요")
    private String password;

    @Email(message = "이메일 형식이 아니에요")
    @NotBlank(message = "이메일이 입력되지 않았어요")
    private String email;

    @Builder
    public MemberDTO(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public void encodePassword(String password){
        this.password = password;
    }
}
