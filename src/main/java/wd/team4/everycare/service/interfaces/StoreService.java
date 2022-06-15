package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.store.StoreAdminViewDTO;
import wd.team4.everycare.dto.store.StoreFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface StoreService {

    Long save(StoreFormDTO storeFormDTO);

    String update(Long id, StoreFormDTO storeFormDTO);

    Store findStoreByMember(String id);

    MyResponse<Object> findAllStores();

    MyResponse<Object> findStore(Long id);

    List<StoreAdminViewDTO> findStoresThatRequireApproval();

    StoreAdminViewDTO webFindStore(Long id);

    ResponseEntity<MyResponse> findAllStatistics(PrincipalDetails principalDetails, LocalDateTime start, LocalDateTime end);

}
