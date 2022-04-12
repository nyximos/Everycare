package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.dto.StoreFormDTO;

public interface StoreService {

    Long save(StoreFormDTO storeFormDTO);

    String update(Long id, StoreFormDTO storeFormDTO);

}
