package wd.team4.everycare.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.PayResponse;
import wd.team4.everycare.dto.response.MyResponse;

public interface PaymentService {
    PayResponse payment(String paymentKey, String orderId, Long amount) throws JsonProcessingException;
}
