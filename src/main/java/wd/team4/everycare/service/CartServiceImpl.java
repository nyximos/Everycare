package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.dto.response.MyResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl {

    public ResponseEntity<MyResponse> add(HttpServletRequest request, Long id, int quantity, int amount) {
        HttpSession session = request.getSession();

        if(session.getAttribute("session")!=null){
            List<>
        }
    }
}
