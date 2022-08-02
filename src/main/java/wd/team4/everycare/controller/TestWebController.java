package wd.team4.everycare.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wd.team4.everycare.dto.PayResponse;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.OrderServiceImpl;
import wd.team4.everycare.service.PaymentServiceImpl;

@Controller
@RequiredArgsConstructor
public class TestWebController {

    private final PaymentServiceImpl paymentService;
    private final OrderServiceImpl orderService;

    @GetMapping("/test")
    public String testPage(@RequestParam String paymentKey, @RequestParam String orderId,
                           @RequestParam Long amount, @RequestParam Long orderTableId) throws JsonProcessingException {
        PayResponse payment = paymentService.payment(paymentKey, orderId, amount);
        ResponseEntity<MyResponse> signOrder = orderService.signOrder(orderTableId, payment);
        return "redirect:https://localhost:8081/successproductpage";
    }
}
