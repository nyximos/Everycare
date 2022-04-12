package wd.team4.everycare.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import wd.team4.everycare.config.jwt.JwtAuthenticationFilter;
import wd.team4.everycare.config.jwt.JwtAuthorizationFilter;
import wd.team4.everycare.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final MemberRepository memberRepository;

    private final CorsConfig corsConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilter(corsConfig.corsFilter())
                .formLogin()
                    .disable()
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository))
                .authorizeRequests()
                .antMatchers("/api/signup/**")
                    .permitAll()
                .antMatchers("/dashboard/**")
                    .access("hasRole('ROLE_MEMBER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/**")
                .permitAll()
               .anyRequest().permitAll();
    }
}
