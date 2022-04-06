package wd.team4.everycare.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDTO {
    private String username;
    private String password;

    @Builder
    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
