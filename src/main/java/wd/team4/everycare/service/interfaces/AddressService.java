package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.address.AddressFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

public interface AddressService {

    ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> get(Long id);

    ResponseEntity<MyResponse> save(PrincipalDetails principalDetails, AddressFormDTO addressFormDTO);

    ResponseEntity<MyResponse> update(Long id, AddressFormDTO addressFormDTO);

    ResponseEntity<MyResponse> remove(Long id);

}
