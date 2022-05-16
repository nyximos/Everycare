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

    public ResponseEntity<MyResponse> add(HttpServletRequest request, Long id, int quantity, int amount) {
        HttpSession session = request.getSession();

        CartDTO cart = new CartDTO(id, quantity, amount);

        if(session.getAttribute("session") == null){
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
}
