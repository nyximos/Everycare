package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetImage;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.ImageDTO;
import wd.team4.everycare.dto.MultipartFileDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDetailDTO;
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.caretarget.CareTargetListViewDTO;
import wd.team4.everycare.dto.caretarget.CareTargetViewDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetImageRepository;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.service.exception.CareTargetNotFoundException;
import wd.team4.everycare.service.interfaces.CareTargetService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CareTargetServiceImpl implements CareTargetService {

    private final FileStoreService fileStoreService;
    private final CareTargetRepository careTargetRepository;
    private final CareTargetImageRepository careTargetImageRepository;

    @Override
    public ResponseEntity<MyResponse> findAll(PrincipalDetails principalDetails) {

        Member user = principalDetails.getUser();

        List<CareTarget> careTargets = careTargetRepository.findAllByMember(user);

        List<CareTargetListViewDTO> careTargetListViewDTOs = new ArrayList<>();
        List<ImageDTO> imageDTOs = new ArrayList<>();

        if (careTargets.isEmpty()) {
            return null;
        }

        for (CareTarget careTarget : careTargets) {
            imageDTOs.clear();

            List<CareTargetImage> images = careTargetImageRepository.findAllByCareTarget(careTarget);

            for (CareTargetImage image : images) {
                ImageDTO imageDTO = ImageDTO.builder()
                        .id(image.getId())
                        .uploadFileName(image.getUploadFileName())
                        .storeFileName(image.getStoreFileName())
                        .build();
                imageDTOs.add(imageDTO);
            }

            CareTargetListViewDTO careTargetListViewDTO = CareTargetListViewDTO.builder()
                    .id(careTarget.getId())
                    .name(careTarget.getName())
                    .imageDTOs(imageDTOs)
                    .build();

            careTargetListViewDTOs.add(careTargetListViewDTO);
        }

        MyResponse<List<CareTargetListViewDTO>> body = MyResponse.<List<CareTargetListViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careTargetListViewDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<MyResponse> findById(Long id) {

        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        CareTarget careTargetEntity = careTarget.orElse(null);

        List<CareTargetImage> images = careTargetImageRepository.findAllByCareTarget(careTargetEntity);

        List<ImageDTO> imageDTOs = new ArrayList<>();

        for (CareTargetImage image : images) {
            ImageDTO imageDTO = ImageDTO.builder()
                    .id(image.getId())
                    .uploadFileName(image.getUploadFileName())
                    .storeFileName(image.getStoreFileName())
                    .build();
            imageDTOs.add(imageDTO);
        }


        CareTargetDetailDTO careTargetDetailDTO = CareTargetDetailDTO.builder()
                .id(careTargetEntity.getId())
                .name(careTargetEntity.getName())
                .gender(careTargetEntity.getGender())
                .birth(careTargetEntity.getBirth())
                .height(careTargetEntity.getHeight())
                .weight(careTargetEntity.getWeight())
                .zipcode(careTargetEntity.getZipcode())
                .address(careTargetEntity.getAddress())
                .detailedAddress(careTargetEntity.getDetailedAddress())
                .longTermCareGrade(careTargetEntity.getLongTermCareGrade())
                .comment(careTargetEntity.getComment())
                .pet(careTargetEntity.getPet())
                .isCctvAgreement(careTargetEntity.getIsCctvAgreement())
                .careType(careTargetEntity.getCareType())
                .coronaTest(careTargetEntity.getCoronaTest())
                .imageDTOs(imageDTOs)
                .build();

        MyResponse<CareTargetDetailDTO> body = MyResponse.<CareTargetDetailDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careTargetDetailDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);    }

    @Override
    public ResponseEntity<MyResponse> saveImage(Long id, MultipartFileDTO imageDTO) throws IOException {

        List<UploadFile> attachFiles = fileStoreService.storeFiles(imageDTO.getAttachFiles());

        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        CareTarget careTargetEntity = careTarget.orElse(null);

        for (UploadFile file : attachFiles) {

            CareTargetImage careTargetImage = CareTargetImage.builder()
                    .uploadFileName(file.getUploadFileName())
                    .storeFileName(file.getStoreFileName())
                    .careTarget(careTargetEntity)
                    .build();

            careTargetImageRepository.save(careTargetImage);
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }

    @Override
    public ResponseEntity<MyResponse> removeImage(Long id) {

        careTargetImageRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }

    @Override
    public Long save(CareTargetFormDTO careTargetFormDTO) throws IOException {

        CareTarget careTarget = careTargetDtoToEntity(careTargetFormDTO);
        careTargetRepository.save(careTarget);
        List<UploadFile> attachFiles = fileStoreService.storeFiles((careTargetFormDTO.getAttachFiles()));

        for (UploadFile file : attachFiles) {
            CareTargetImage careTargetImage = careTargetDtoToImage(careTarget, file);
            careTargetImageRepository.save(careTargetImage);
        }
        return careTarget.getId();
    }

    @Override
    public CareTargetImage careTargetDtoToImage(CareTarget careTarget, UploadFile attachFile) throws IOException{
        return CareTargetImage.builder()
                .uploadFileName(attachFile.getUploadFileName())
                .storeFileName(attachFile.getStoreFileName())
                .careTarget(careTarget)
                .build();
    }

    @Override
    public CareTarget careTargetDtoToEntity(CareTargetFormDTO careTargetFormDTO) {
        return CareTargetService.super.careTargetDtoToEntity(careTargetFormDTO);
    }

    @Override
    public void removeCareTarget(Long id) {
        careTargetRepository.deleteById(id);
    }

    @Override
    public CareTarget isPresent(Long id) {
        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        return careTarget.get();
    }

    @Override
    public boolean isEmpty(Long id) {
        if(careTargetRepository.findById(id).isEmpty()){
            return true;
        } throw new CareTargetNotFoundException(id);
    }

//    @Override
//    public List<CareTarget> findCareTargets(String id){
//        Optional<Member> member = memberRepository.findById(id);
//        return member.get().getCareTargets();
//    }

    @Override
    public List<CareTargetImage> findCareTargetImages(Long id) {
        CareTarget careTarget = isPresent(id);
        return careTarget.getCareTargetImages();
    }

    @Override
    public String update(Long id, CareTargetFormDTO careTargetFormDTO) {
        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        CareTarget careTargetEntity = careTarget.orElse(null);
        System.out.println(careTargetEntity.getName());
        if(careTargetEntity == null) {
            System.out.println("케어 대상인이 없으므로 종료");
            return "실패";
        }
        careTargetEntity.updateInfo(careTargetFormDTO);
        return "수정완료";
    }

    @Override
    public CareTargetViewDTO webFindCareTarget(Long id) {
        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        if(careTarget.isEmpty()) return null;
        CareTarget careTargetEntity = careTarget.orElse(null);
        return careTargetEntity.toViewDTO();
    }

}
