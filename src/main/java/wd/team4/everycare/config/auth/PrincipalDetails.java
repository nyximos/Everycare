package wd.team4.everycare.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Store;

import java.util.ArrayList;
import java.util.Collection;

/*
    시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행
    로그인 진행 완료시 Security session을 생성,  Security ContextHolder 라는 키값에 세션 정보를 저장
    시큐리티 session에 들어갈 수 있는 오브젝트 타입 -> Authentication 타입 객체
    Authentication 안에 User 정보가 있어야 됨.
    User 오브젝트 타입 -> UserDetails 타입 객체 , OAuth2User 타입도 가능
 */

public class PrincipalDetails implements UserDetails{

    private Member member;

    public PrincipalDetails(Member member){
        this.member = member;
    }

    public Member getUser() {
        return member;
    }

    public Store getStore() { return member.getStore(); }

    public String getRole() { return member.getRole().toString(); }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().getAuthority()));
        return authorities;
    }
}
