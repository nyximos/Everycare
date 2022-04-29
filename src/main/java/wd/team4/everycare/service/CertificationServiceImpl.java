package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Certification;
import wd.team4.everycare.dto.CertificationViewDTO;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.CertificationRepository;
import wd.team4.everycare.service.interfaces.CertificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;
//    private final CareSitterRepository careSitterRepository;

    @Override
    public List<CertificationViewDTO> findAllByCareSitter(Long id) {

//        Optional<CareSitter> careSitter = careSitterRepository.findById(id);
//        CareSitter careSitterEntity = careSitter.orElse(null);
        List<Certification> certifications = certificationRepository.findAllByCareSitterId(id);
        List<CertificationViewDTO> certificationViewDTOs = new ArrayList<>();

        if(certifications.isEmpty()){
            return null;
        }

        certifications.stream().map(certification -> certification.toViewDTO()).forEach(certificationViewDTOs::add);

        return certificationViewDTOs;
    }
}
