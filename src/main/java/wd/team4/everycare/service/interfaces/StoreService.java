package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.StoreAdminViewDTO;
import wd.team4.everycare.dto.StoreFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.List;

public interface StoreService {

    Long save(StoreFormDTO storeFormDTO);

    String update(Long id, StoreFormDTO storeFormDTO);

    Store findStoreByMember(String id);

    MyResponse<Object> findAllStores();

    MyResponse<Object> findStore(Long id);

    List<StoreAdminViewDTO> findStoresThatRequiresApproval();
}
