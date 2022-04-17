package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.StoreFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.StoreRepository;
import wd.team4.everycare.service.interfaces.StoreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    private final MemberRepository memberRepository;
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
        if (storeEntity == null) {
            System.out.println("없음");
            return "실패";
        }
        storeEntity.updateInfo(storeFormDTO);
        return null;
    }

    @Override
    public Store findStoreByMember(String id) {
        Optional<Member> member = memberRepository.findById(id);
        Store store = member.get().getStore();
        return store;
    }

    @Override
    public MyResponse<Object> findAllStores() {
        List<Store> stores = storeRepository.findAll();
        // ResponseEntity 의 body 에 myResponse가 들어감.
        return MyResponse.builder()
                .header(StatusEnum.OK)
                .body(stores)
                .message("ㅅㄱ").build();

    }

    @Override
    public MyResponse<Object> findStore(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        Store storeEntity = store.orElse(null);// 있으면 get() 엔티티꺼내고, 없으면 null을 넣는다.
        if (storeEntity == null) {
            return MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("스토어가 존재하지 않습니다.").build();
        }
        StoreFormDTO storeFormDTO = StoreFormDTO.builder()
                .id(storeEntity.getId())
                .name(storeEntity.getName())
                .url(storeEntity.getUrl())
                .businessLicenseNumber(storeEntity.getBusinessLicenseNumber())
                .email(storeEntity.getEmail())
                .operationStartTime(storeEntity.getOperationStartTime())
                .operationEndTime(storeEntity.getOperationEndTime())
                .lunchStartTime(storeEntity.getLunchStartTime())
                .lunchEndTime(storeEntity.getLunchEndTime())
                .closedDay(storeEntity.getClosedDay())
                .companyCorporationName(storeEntity.getCompanyCorporationName())
                .representativeName(storeEntity.getRepresentativeName())
                .businessLocation(storeEntity.getBusinessLocation())
                .customerServiceNumber(storeEntity.getCustomerServiceNumber())
                .build();
        return MyResponse.builder()
                .header(StatusEnum.OK)
                .body(storeFormDTO)
                .message("스토어 조회 완료").build();
    }

}
