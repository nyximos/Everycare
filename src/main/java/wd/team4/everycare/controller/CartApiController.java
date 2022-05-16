package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CartServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartApiController {

    private final CartServiceImpl cartService;

    @PostMapping("/products/{id}/cart")
    public ResponseEntity<MyResponse> addCart(@PathVariable("id") Long id,
                                              @RequestParam int quantity,
                                              @RequestParam int amount,
                                              HttpServletRequest request){
        cartService.add(request, id, quantity, amount);
    }
}
