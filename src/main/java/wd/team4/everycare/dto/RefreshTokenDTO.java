package wd.team4.everycare.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshTokenDTO {
    private String username;
    private String refresh_token;

    public RefreshTokenDTO(String username, String refresh_token) {
        this.username = username;
        this.refresh_token = refresh_token;
    }
}
