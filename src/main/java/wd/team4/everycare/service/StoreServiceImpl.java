package wd.team4.everycare.service;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.OrderProduct;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.StatisticsResultDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.dto.store.StatisticsDTO;
import wd.team4.everycare.dto.store.StoreAdminViewDTO;
import wd.team4.everycare.dto.store.StoreFormDTO;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.StoreRepository;
import wd.team4.everycare.repository.query.OrderProductQueryRepository;
import wd.team4.everycare.repository.query.ProductQueryRepository;
import wd.team4.everycare.service.interfaces.StoreService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final OrderProductQueryRepository orderProductQueryRepository;
    private final ProductQueryRepository productQueryRepository;

    @Override
    public Long save(StoreFormDTO storeFormDTO) {
        LocalDateTime time = LocalDateTime.now();

        Store store = storeFormDTO.toStore();
        store.approvedByAdmin(0, null);
        store.saveTime(time);
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

        /*
        // List<Store>가 있는지 확인!
        // DTO 배열 만들기
        List<StoreFormDTO> storeFormDTOs = new ArrayList<>();
        // DTO 배열에 변환해서 넣기
        for(Store s : stores) {
            // DTO로 변경
            StoreFormDTO storeFormDTO = s.toDTO();
            // DTO를 배열에 넣기
            storeFormDTOs.add(storeFormDTO);
        }

        stores.stream().map(store -> store.toDTO).forEach(storeFormDTOs::add);

        */

        // ResponseEntity 의 body 에 myResponse가 들어감.
        return MyResponse.builder()
                .header(StatusEnum.OK)
                .body(stores)
                .message("성공").build();
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
        StoreFormDTO storeFormDTO = storeEntity.toFormDTO();
        return MyResponse.builder()
                .header(StatusEnum.OK)
                .body(storeFormDTO)
                .message("스토어 조회 완료").build();
    }

    @Override
    public List<StoreAdminViewDTO> findStoresThatRequireApproval() {
        List<Store> stores = storeRepository.findAllByAdminApproval(0);
        List<StoreAdminViewDTO> storeAdminViewDTOs = new ArrayList<>();

        if (stores.isEmpty()) {
            return null;
        }

        stores.stream().map(store -> store.toAdminViewDTO()).forEach(storeAdminViewDTOs::add);

        return storeAdminViewDTOs;
    }

    @Override
    public StoreAdminViewDTO webFindStore(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        Store storeEntity = store.orElse(null);// 있으면 get() 엔티티꺼내고, 없으면 null을 넣는다.
        if (storeEntity == null) {
            return null;
        } else {
            StoreAdminViewDTO storeAdminViewDTO = storeEntity.toAdminViewDTO();
            return storeAdminViewDTO;
        }
    }

    @Override
    public ResponseEntity<MyResponse> findAllStatistics(PrincipalDetails principalDetails, String start, String end) {
        Member member = principalDetails.getUser();
        List<Store> storeList = storeRepository.findByMember(member);

        LocalDateTime startTime = StringToLocalDateTime(start);
        LocalDateTime endTime = StringToLocalDateTime(end);


        HashMap<String, Object> statisticsItem = new HashMap<>();
        statisticsItem.put("1", 0);
        statisticsItem.put("2", 0);
        statisticsItem.put("3", 0);
        statisticsItem.put("4", 0);
        statisticsItem.put("5", 0);
        statisticsItem.put("6", 0);
        statisticsItem.put("7", 0);
        statisticsItem.put("8", 0);
        statisticsItem.put("9", 0);
        statisticsItem.put("10", 0);
        statisticsItem.put("11", 0);
        statisticsItem.put("12", 0);

        HashMap<Integer, LocalDateTime> result = new HashMap<>();


        for (Store store : storeList) {
            List<Tuple> statistics = orderProductQueryRepository.findStatistics(startTime, endTime, store);
            System.out.println("statistics = " + statistics);

            for (Tuple tuple : statistics) {

                Integer amount = tuple.get(0, Integer.class);
                LocalDateTime payTime = tuple.get(1, LocalDateTime.class);
                int month = payTime.getMonthValue();

                switch (month) {
                    case 1:
                        result.put(amount, payTime);
                        statisticsItem.put("1", result);
                        break;
                    case 2:
                        result.put(amount, payTime);
                        statisticsItem.put("2", result);
                        break;
                    case 3:
                        result.put(amount, payTime);
                        statisticsItem.put("3", result);
                        break;
                    case 4:
                        result.put(amount, payTime);
                        statisticsItem.put("4", result);
                        break;
                    case 5:
                        result.put(amount, payTime);
                        statisticsItem.put("5", result);
                        break;
                    case 6:
                        result.put(amount, payTime);
                        statisticsItem.put("6", result);
                        break;
                    case 7:
                        result.put(amount, payTime);
                        statisticsItem.put("7", result);
                        break;
                    case 8:
                        result.put(amount, payTime);
                        statisticsItem.put("8", result);
                        break;
                    case 9:
                        result.put(amount, payTime);
                        statisticsItem.put("9", result);
                        break;
                    case 10:
                        result.put(amount, payTime);
                        statisticsItem.put("10", result);
                        break;
                    case 11:
                        result.put(amount, payTime);
                        statisticsItem.put("11", result);
                        break;
                    case 12:
                        result.put(amount, payTime);
                        statisticsItem.put("12", result);
                        break;
                }
                System.out.println("statisticsItem = " + statisticsItem);
                System.out.println("statisticsItem = " + statisticsItem.get("6"));
            }
        }

        MyResponse body = MyResponse.builder()

                .header(StatusEnum.OK)
                .message("총 매출 통계")
                .body(statisticsItem)
                .build();
        System.out.println(statisticsItem.get("1"));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<MyResponse> findSalesByProduct(Long id, String start, String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        LocalDateTime startTime = startDate.atStartOfDay();
        LocalDateTime endTime = StringToLocalDateTime(end);

        System.out.println("startDateTime = " + startTime);
        System.out.println("endDateTime = " + endTime);


        List<Integer> list = productQueryRepository.findSalesByProduct(id, startTime, endTime);

        int sum = 0;
        for (Integer integer : list) {
            int i = integer.intValue();
            sum += i;
        }

        MyResponse<Integer> body = MyResponse.<Integer>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(sum)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);

    }

    private LocalDateTime StringToLocalDateTime(String LocalDateTimeStr) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(LocalDateTimeStr, dateTimeFormatter);
        LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
        System.out.println(ldt);
        return ldt;
    }

}
