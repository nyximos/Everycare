package wd.team4.everycare.config.jwt;

public interface JwtProperties {
    String SECRET = "team4"; // 우리 서버만 알고 있는 비밀값
    int EXPIRATION_TIME = 1000 * 60 * 10; // 10분
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
