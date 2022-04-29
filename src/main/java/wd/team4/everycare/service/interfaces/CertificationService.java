package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.dto.CertificationViewDTO;

import java.util.List;

public interface CertificationService {

    List<CertificationViewDTO> findAllByCareSitter(Long id);
}
