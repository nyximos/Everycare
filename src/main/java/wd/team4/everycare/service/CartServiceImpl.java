package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.dto.product.CartDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl {

    public ResponseEntity<MyResponse> getAll(HttpServletRequest request) {
        Object cart = request.getSession().getAttribute("cart");

        MyResponse<Object> body = MyResponse.<Object>builder()
                .header(StatusEnum.OK)
                .body(cart)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    public ResponseEntity<MyResponse> add(HttpServletRequest request, Long id, int quantity, int amount) {
        HttpSession session = request.getSession();

        CartDTO cart = new CartDTO(id, quantity, amount);

        if(session.getAttribute("cart") == null){
            List<CartDTO> cartList = new ArrayList<>();
            cartList.add(cart);
            session.setAttribute("cart", cartList);
        } else {
            List<CartDTO> cartList = (List<CartDTO>) session.getAttribute("cart");
            cartList.add(cart);
            session.setAttribute("cart", cartList);
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    public ResponseEntity<MyResponse> remove(HttpServletRequest request, int id) {
        List<CartDTO> cartList = (List<CartDTO>)request.getSession().getAttribute("cart");
        cartList.remove(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    public ResponseEntity<MyResponse> removeAll(HttpServletRequest request) {
        List<CartDTO> cartList = (List<CartDTO>)request.getSession().getAttribute("cart");
        cartList.removeAll(cartList);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
