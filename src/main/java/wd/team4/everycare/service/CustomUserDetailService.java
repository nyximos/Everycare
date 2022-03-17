package wd.team4.everycare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.repository.MemberRepository;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
