package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.WishListServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WishListAPIController {

    private final WishListServiceImpl wishListService;

    @GetMapping("/dashboard/wishlist")
    public ResponseEntity<MyResponse> getWishList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> responseEntity = wishListService.getAll(principalDetails);
        return responseEntity;
    }

    @PostMapping("/products/{id}/wish")
    public ResponseEntity<MyResponse> saveWishList(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> responseEntity = wishListService.save(id, principalDetails);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/wishlist/{id}")
    public ResponseEntity<MyResponse> deleteWishList(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = wishListService.remove(principalDetails,id);
        return responseEntity;
    }

}
