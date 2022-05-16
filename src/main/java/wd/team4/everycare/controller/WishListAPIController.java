package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.WishListServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WishListAPIController {

    private final WishListServiceImpl wishListService;

    @PostMapping("/products/{id}/wish")
    public ResponseEntity<MyResponse> saveWishList(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        wishListService.save(id, principalDetails);
    }
}
