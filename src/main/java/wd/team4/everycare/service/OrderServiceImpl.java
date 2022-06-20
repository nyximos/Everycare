package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.PayResponse;
import wd.team4.everycare.dto.order.OrderDTO;
import wd.team4.everycare.dto.order.OrderProductDTO;
import wd.team4.everycare.dto.order.SignOrderDTO;
import wd.team4.everycare.dto.product.CartDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.OrderProductRepository;
import wd.team4.everycare.repository.OrderRepository;
import wd.team4.everycare.repository.ProductRepository;
import wd.team4.everycare.repository.query.OrderProductQueryRepository;
import wd.team4.everycare.service.interfaces.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderProductQueryRepository orderProductQueryRepository;

    @Override
    public ResponseEntity<MyResponse> order(HttpServletRequest request, PrincipalDetails principalDetails, OrderDTO orderDTO) {

        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("장바구니가 비었습니다.")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
        }

        List<CartDTO> cartDTOs = (List<CartDTO>) session.getAttribute("cart");

        int total = 0;

        for (int i = 0; i < cartDTOs.size(); i++) {
            total += cartDTOs.get(i).getAmount();
        }

        Order order = Order.builder()
                .name(orderDTO.getName())
                .amount(total)
                .recipientName(orderDTO.getRecipientName())
                .recipientNumber(orderDTO.getRecipientNumber())
                .zipcode(orderDTO.getZipcode())
                .address(orderDTO.getAddress())
                .detailedAddress(orderDTO.getDetailedAddress())
                .status(OrderStatus.ORDER)
                .orderTime(LocalDateTime.now())
                .comment(orderDTO.getComment())
                .member(principalDetails.getUser())
                .build();

        orderRepository.save(order);

        for (CartDTO cartDTO : cartDTOs) {

            Optional<Product> product = productRepository.findById(cartDTO.getId());
            Product productEntity = product.orElse(null);

            productEntity.removeStock(cartDTO.getQuantity());

            OrderProduct orderProduct = OrderProduct.builder()
                    .quantity(cartDTO.getQuantity())
                    .amount(cartDTO.getAmount())
                    .review(0)
                    .order(order)
                    .product(productEntity)
                    .build();

            orderProductRepository.save(orderProduct);
        }

        session.removeAttribute("cart");

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(order.getId())
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<MyResponse> signOrder(Long orderId, PayResponse payResponse) {
        int totalAmount = payResponse.getTotalAmount();
        LocalDateTime approvedAt = payResponse.getApprovedAt();
        String cardCompany = payResponse.getCardCompany();
        String cardNumber = payResponse.getCardNumber();
        String approveNo = payResponse.getApproveNo();
        int installmentPlanMonths = payResponse.getInstallmentPlanMonths();
        Order order = orderRepository.findById(orderId).orElse(null);
        System.out.println("order = " + order);
        System.out.println("totalAmount, = " + totalAmount);
        System.out.println("totalAmount, = " + approvedAt);
        System.out.println("totalAmount, = " + cardCompany);
        System.out.println("totalAmount, = " + cardNumber);
        System.out.println("totalAmount, = " + approveNo);
        System.out.println("totalAmount, = " + installmentPlanMonths);
        System.out.println("totalAmount, = " + totalAmount);


        order.pay(totalAmount, approvedAt, cardCompany, cardNumber, Integer.parseInt(approveNo), installmentPlanMonths);

        SignOrderDTO signOrderDTO = order.toSignOrderDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .body(signOrderDTO)
                .message("결제 및 DB 수정 성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getCompleteOrder(PrincipalDetails principalDetails) {

        Member member = principalDetails.getUser();

        List<OrderProduct> results = orderProductQueryRepository.findResult(member, OrderStatus.COMPLETE);

        List<OrderProductDTO> orderProductDTOs = new ArrayList<>();

        results.stream().map(result -> result.toOrderProductDTO()).forEach(orderProductDTOs::add);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .body(orderProductDTOs)
                .message("결제한 상품 목록 조회")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<MyResponse> findOrderProduct(Long orderId, PrincipalDetails principalDetails) {
        Member member = principalDetails.getUser();

        List<OrderProduct> findOrder = orderProductRepository.findByOrderId(orderId);
        List<OrderProductDTO> orderProductDTOs = new ArrayList<>();

        findOrder.stream().map(orderProduct -> orderProduct.toOrderProductDTO()).forEach(orderProductDTOs::add);

        for (OrderProductDTO orderProductDTO: orderProductDTOs) {
            orderProductDTO.setMember(member.toMemberNameDTO());
        }


        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("결제할 목록")
                .body(orderProductDTOs)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}