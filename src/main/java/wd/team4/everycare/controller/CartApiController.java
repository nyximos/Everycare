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

    @GetMapping("/cart")
    public ResponseEntity<MyResponse> getAll(HttpServletRequest request) {
        ResponseEntity<MyResponse> responseEntity = cartService.getAll(request);
        return responseEntity;
    }

    @PostMapping("/products/{id}/cart")
    public ResponseEntity<MyResponse> addCart(HttpServletRequest request,
                                              @PathVariable("id") Long id,
                                              @RequestParam int quantity,
                                              @RequestParam int amount){
        ResponseEntity<MyResponse> responseEntity = cartService.add(request, id, quantity, amount);
        return responseEntity;
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<MyResponse> remove(HttpServletRequest request,
                                             @PathVariable("id") int id){
        ResponseEntity<MyResponse> responseEntity = cartService.remove(request, id);
        return responseEntity;
    }

    @DeleteMapping("/cart/all")
    public ResponseEntity<MyResponse> removeAll(HttpServletRequest request){
        ResponseEntity<MyResponse> responseEntity = cartService.removeAll(request);
        return responseEntity;
    }
}
