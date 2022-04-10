package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetImage;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.CareTargetFormDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.repository.CareTargetImageRepository;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.exception.CareTargetNotFoundException;
import wd.team4.everycare.service.interfaces.CareTargetService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareTargetServiceImpl implements CareTargetService {

    private final FileStoreService fileStoreService;
    private final CareTargetRepository careTargetRepository;
    private final CareTargetImageRepository careTargetImageRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long save(CareTargetFormDTO careTargetFormDTO) throws IOException {

        CareTarget careTarget = careTargetDtoToEntity(careTargetFormDTO);
        careTargetRepository.save(careTarget);
        System.out.println("=================================");
        System.out.println("careTarget = " + careTarget.getName());

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

    @Override
    public List<CareTarget> findCareTargets(String id){
        Optional<Member> member = memberRepository.findById(id);
        return member.get().getCareTargets();

    }

    @Override
    public List<CareTargetImage> findCareTargetImages(Long id) {
        CareTarget careTarget = isPresent(id);
        return careTarget.getCareTargetImages();
    }

}
