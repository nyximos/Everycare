package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.dto.order.OrderDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {

    private final OrderServiceImpl orderService;

    @PostMapping("/cart/orders")
    public ResponseEntity<MyResponse> order(HttpServletRequest request, @ModelAttribute OrderDTO orderDTO) {
        ResponseEntity<MyResponse> responseEntity = orderService.order(request, orderDTO);
        return responseEntity;
    }

    @GetMapping("/cart/orders/payments")
    public ResponseEntity<MyResponse> orderPay(@RequestParam String paymentKey, @RequestParam String orderId,
            @RequestParam Long amount, @RequestParam Long orderTableId) throws JsonProcessingException {
        PayResponse payment = paymentService.payment(paymentKey, orderId, amount);
        ResponseEntity<MyResponse> signOrder = orderService.signOrder(orderTableId, payment);
        return signOrder;
    }
}
