package wd.team4.everycare.domain;

import org.springframework.security.core.GrantedAuthority;

public enum MemberRole implements GrantedAuthority {
    ROLE_MEMBER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
