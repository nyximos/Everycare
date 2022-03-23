package wd.team4.everycare.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@DynamicInsert
@Entity
@Table(name = "handson_env")
@SequenceGenerator(name = "handson_env_seq_generator",
        sequenceName = "handson_env_seq",
        initialValue = 1, allocationSize = 1)
public class HandsOnEnv implements Serializable {

    private static final long serialVersionUID = -3423961242822117508L;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "handson_env_seq_generator")
    private int num;

    @Column(name = "os_user")
    private String osuser;

    private String username;

    private String email;

    private String tenant;

    private String region;

    private String cloud_account;

    @Column(name = "cloud_account_pw")
    private String cloud_account_pw;

    @Column(name = "handson_doc_url")
    private String handson_doc_url;

    @Column(name = "key_download_url")
    private String key_download_url;

    @Column(name = "env_ip")
    private String env_ip;

    @Column(name = "env_name")
    private String env_name;

}
