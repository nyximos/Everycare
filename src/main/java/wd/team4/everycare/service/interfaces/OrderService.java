package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.order.OrderDTO;
import wd.team4.everycare.dto.response.MyResponse;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {

    ResponseEntity<MyResponse> order(HttpServletRequest request, PrincipalDetails principalDetails, OrderDTO orderDTO);
    ResponseEntity<MyResponse> signOrder(Long orderId, PayResponse payResponse);
}
