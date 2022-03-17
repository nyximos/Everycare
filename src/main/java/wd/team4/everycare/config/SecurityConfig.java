package wd.team4.everycare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/member/**").hasAnyRole("MEMBER","ADMIN")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .and()
                .formLogin()
                    .and()
                .logout()
                    .and()
                .csrf()
                    .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$KKZ0C.KJxbqNFfmuGz0MNunkK7v1IvuSbHlXLWLuWuYFe87S.F5Yu")
                .roles("MEMBER");

        auth.inMemoryAuthentication().withUser("admin1")
                .password("$2a$10$KKZ0C.KJxbqNFfmuGz0MNunkK7v1IvuSbHlXLWLuWuYFe87S.F5Yu")
                .roles("ADMIN");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/js/**","/css/**",
                "/images/**","/favicon.ico");
    }
}