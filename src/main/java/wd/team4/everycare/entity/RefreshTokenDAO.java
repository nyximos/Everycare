package wd.team4.everycare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "jwt_refresh_token")
public class RefreshTokenDAO implements Serializable {

    private static final long serialVersionUID = -3423961242822117508L;

    @Id
    private String username;

    @JsonIgnore
    @Column(name = "refresh_token")
    private String refreshToken;

}