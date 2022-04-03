package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetImage;
import wd.team4.everycare.dto.CareTargetFormDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.repository.CareTargetImageRepository;
import wd.team4.everycare.repository.CareTargetRepository;
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

    @Override
    public Long save(CareTargetFormDTO careTargetFormDTO) throws IOException {

        CareTarget careTarget = careTargetDtoToEntity(careTargetFormDTO);
        careTargetRepository.save(careTarget);

        UploadFile attachFile = fileStoreService.storeFile(careTargetFormDTO.getAttachFile());
        List<UploadFile> attachFiles = fileStoreService.storeFiles((careTargetFormDTO.getAttachFiles()));

        CareTargetImage careTargetImage = careTargetDtoToImage(careTarget, attachFile);
        careTargetImageRepository.save(careTargetImage);

        for (UploadFile file : attachFiles) {
            CareTargetImage careTargetImage2 = careTargetDtoToImage(careTarget, file);
            careTargetImageRepository.save(careTargetImage2);
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
    public List<CareTargetImage> findCareTargetImages(Long id) {
        CareTarget careTarget = isPresent(id);
        return careTarget.getCareTargetImages();
    }

}
