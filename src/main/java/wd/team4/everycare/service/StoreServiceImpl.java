package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.StoreFormDTO;
import wd.team4.everycare.repository.StoreRepository;
import wd.team4.everycare.service.interfaces.StoreService;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Long save(StoreFormDTO storeFormDTO) {
        Store store = storeFormDTO.toStore();
        storeRepository.save(store);

        return store.getId();
    }

}
