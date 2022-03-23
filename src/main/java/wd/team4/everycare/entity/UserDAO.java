package wd.team4.everycare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserDAO implements Serializable {

    private static final long serialVersionUID = -3423961242822117508L;

    @Id
    private String username;

    @JsonIgnore
    private String password;

}