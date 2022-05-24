package wd.team4.everycare.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.PayResponse;
import wd.team4.everycare.dto.order.OrderDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.OrderServiceImpl;
import wd.team4.everycare.service.PaymentServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {

    private final OrderServiceImpl orderService;
    private final PaymentServiceImpl paymentService;

    @PostMapping("/cart/orders")
    public ResponseEntity<MyResponse> order(HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute OrderDTO orderDTO) {
        ResponseEntity<MyResponse> responseEntity = orderService.order(request, principalDetails,orderDTO);
        return responseEntity;
    }

    @GetMapping("/cart/orders/payments")
    public ResponseEntity<MyResponse> orderPay(@RequestParam String paymentKey, @RequestParam String orderId,
            @RequestParam Long amount, @RequestParam Long orderTableId) throws JsonProcessingException {
        PayResponse payment = paymentService.payment(paymentKey, orderId, amount);
        ResponseEntity<MyResponse> signOrder = orderService.signOrder(orderTableId, payment);
        return signOrder;
    }

    @GetMapping("/dashboard/orders")
    public ResponseEntity<MyResponse> completeOrder(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> completeOrder = orderService.getCompleteOrder(principalDetails);

        return completeOrder;
    }

}
