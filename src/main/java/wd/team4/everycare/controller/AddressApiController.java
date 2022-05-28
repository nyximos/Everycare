package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.address.AddressFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.AddressServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AddressApiController {

    private final AddressServiceImpl addressService;

    @GetMapping("/adresses")
    public ResponseEntity<MyResponse> getAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> responseEntity = addressService.getAll(principalDetails);
        return responseEntity;
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = addressService.get(id);
        return responseEntity;
    }

    @PostMapping("/adresses")
    public ResponseEntity<MyResponse> save(@AuthenticationPrincipal PrincipalDetails principalDetails, AddressFormDTO addressFormDTO) {
        ResponseEntity<MyResponse> responseEntity = addressService.save(principalDetails, addressFormDTO);
        return responseEntity;
    }

    @PatchMapping("/adresses/{id}")
    public ResponseEntity<MyResponse> update(@PathVariable("id") Long id,AddressFormDTO addressFormDTO) {
        ResponseEntity<MyResponse> responseEntity = addressService.update(id, addressFormDTO);
        return responseEntity;
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<MyResponse> remove(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = addressService.remove(id);
        return responseEntity;
    }
}
