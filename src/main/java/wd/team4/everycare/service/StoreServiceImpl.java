package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.StoreFormDTO;
import wd.team4.everycare.repository.StoreRepository;
import wd.team4.everycare.service.interfaces.StoreService;

import java.util.Optional;

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

    @Override
    public String update(Long id, StoreFormDTO storeFormDTO) {
        Optional<Store> store = storeRepository.findById(id);
        Store storeEntity = store.orElse(null);
        if(storeEntity == null){
            System.out.println("없음");
            return "실패";
        }
        storeEntity.updateInfo(storeFormDTO);
        return null;
    }

}
