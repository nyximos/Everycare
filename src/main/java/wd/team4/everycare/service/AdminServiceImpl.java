package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.certification.CertificationAdminDetailDTO;
import wd.team4.everycare.dto.certification.CertificationAdminListDTO;
import wd.team4.everycare.dto.member.MemberNameDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.dto.store.StoreAdminDetailDTO;
import wd.team4.everycare.dto.store.StoreAdminListDTO;
import wd.team4.everycare.repository.BoardRepository;
import wd.team4.everycare.repository.CertificationRepository;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.StoreRepository;
import wd.team4.everycare.service.interfaces.AdminService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final CertificationRepository certificationRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<MyResponse> approveAdmin(String id) {

        LocalDateTime time = LocalDateTime.now();

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);

        memberEntity.registrationAdmin(time);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<MyResponse> removeAdmin(String id) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);
        memberEntity.removeAdmin();


        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<MyResponse> getStores() {

        List<Store> stores = storeRepository.findAllByAdminApproval(0);

        List<StoreAdminListDTO> storeAdminListDTOs = new ArrayList<>();

        if (stores.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("등록한 스토어가 없습니다.")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        } else {

            for (Store store : stores) {
                StoreAdminListDTO dto = StoreAdminListDTO.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .memberId(store.getMember().getId())
                        .build();

                storeAdminListDTOs.add(dto);
            }

            MyResponse<List<StoreAdminListDTO>> body = MyResponse.<List<StoreAdminListDTO>>builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .body(storeAdminListDTOs)
                    .build();

            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<MyResponse> getStore(Long id) {

        Optional<Store> store = storeRepository.findById(id);
        Store storeEntity = store.orElse(null);

        Member member = storeEntity.getMember();
        MemberNameDTO memberNameDTO = MemberNameDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .build();

        StoreAdminDetailDTO storeAdminViewDTO = StoreAdminDetailDTO.builder()
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
                .createdAt(storeEntity.getCreatedAt())
                .member(memberNameDTO)
                .build();

        MyResponse<StoreAdminDetailDTO> body = MyResponse.<StoreAdminDetailDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(storeAdminViewDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getCertifications() {

        List<Certification> certifications = certificationRepository.findAllByAdminApproval(0);

        List<CertificationAdminListDTO> certificationAdminListDTOs = new ArrayList<>();

        for (Certification certification : certifications) {

            CareSitter careSitter = certification.getCareSitter();

            CertificationAdminListDTO certificationAdminListDTO = CertificationAdminListDTO.builder()
                    .id(certification.getId())
                    .name(certification.getName())
                    .memberId(careSitter.getMember().getId())
                    .memberName(careSitter.getName())
                    .build();

            certificationAdminListDTOs.add(certificationAdminListDTO);
        }

        if (certificationAdminListDTOs.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("등록된 자격증이 없습니다.")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        } else {
            MyResponse<List<CertificationAdminListDTO>> body = MyResponse.<List<CertificationAdminListDTO>>builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .body(certificationAdminListDTOs)
                    .build();

            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> approveStore(Long id) {
        LocalDateTime time = LocalDateTime.now();
        Optional<Store> store = storeRepository.findById(id);
        Store storeEntity = store.orElse(null);
        if (storeEntity == null) {
            MyResponse<Object> body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("스토어가 존재하지 않습니다.").build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
        } else {
            storeEntity.approvedByAdmin(1, time);

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> approveCertification(Long id, Member admin) {
        LocalDateTime time = LocalDateTime.now();
        Optional<Certification> certification = certificationRepository.findById(id);
        Certification certificationEntity = certification.orElse(null);
        if (certificationEntity == null) {
            MyResponse<Object> body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("자격증이 존재하지 않습니다.").build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
        } else {
            certificationEntity.approvedByAdmin(1, time, admin);

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> writeFAQ(PrincipalDetails principalDetails, BoardDTO boardDTO) {

        Member member = principalDetails.getUser();
        if (member.getRole() == MemberRole.ROLE_ADMIN) {
            Board board = Board.builder()
                    .title(boardDTO.getTitle())
                    .content(boardDTO.getContent())
                    .category(BoardCategory.FAQ)
                    .createdAt(LocalDateTime.now())
                    .count(0)
                    .member(member)
                    .build();

            boardRepository.save(board);

            BoardDTO toBoardDTO = board.toBoardDTO();

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("FAQ등록")
                    .body(toBoardDTO)
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<MyResponse> updateFAQ(PrincipalDetails principalDetails, BoardDTO boardDTO) {
        Member member = principalDetails.getUser();

        if (member.getRole() == MemberRole.ROLE_ADMIN) {
            Board board = boardRepository.findById(boardDTO.getId()).orElse(null);
            board.updateInfo(boardDTO);


            BoardDTO toBoardDTO = board.toBoardDTO();

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("FAQ 수정")
                    .body(toBoardDTO)
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
        else return null;
    }

    @Override
    public ResponseEntity<MyResponse> removeFAQ(PrincipalDetails principalDetails, Long id) {
        Member member = principalDetails.getUser();

        if (member.getRole() == MemberRole.ROLE_ADMIN) {
            boardRepository.deleteById(id);
        }
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("FAQ 삭제")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getCertification(Long id) {

        Optional<Certification> certification = certificationRepository.findById(id);
        Certification certificationEntity = certification.orElse(null);

        Member member = certificationEntity.getCareSitter().getMember();

        CertificationAdminDetailDTO certificationDetailDTO = CertificationAdminDetailDTO.builder()
                .id(certificationEntity.getId())
                .name(certificationEntity.getName())
                .uploadName(certificationEntity.getUploadFileName())
                .storeName(certificationEntity.getStoreFileName())
                .createdAt(certificationEntity.getCreatedAt())
                .memberId(member.getId())
                .memberName(member.getName())
                .build();

        MyResponse<CertificationAdminDetailDTO> body = MyResponse.<CertificationAdminDetailDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(certificationDetailDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}
