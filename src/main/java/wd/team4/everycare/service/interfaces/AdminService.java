package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.response.MyResponse;

import java.time.LocalDateTime;

public interface AdminService {

    ResponseEntity<MyResponse> registration(String id, LocalDateTime time);
}
