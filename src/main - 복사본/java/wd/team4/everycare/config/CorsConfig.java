package wd.team4.everycare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import wd.team4.everycare.config.jwt.JwtProperties;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //내 서버가 응답할때 json을 JS에서 처리할 수 있게 할지 설정
        config.addAllowedOriginPattern("*"); // 모든 ip에 응답 허용, 안되면 OriginPattern
        config.addAllowedHeader("*"); // 모든 header에 응답 허용
        config.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 요청을 허용하겠다.
        config.addExposedHeader(JwtProperties.HEADER_STRING);
        source.registerCorsConfiguration("/**", config); // api 경로 요청들은 config 설정을 따른다.
        return new CorsFilter(source);
    }

}
