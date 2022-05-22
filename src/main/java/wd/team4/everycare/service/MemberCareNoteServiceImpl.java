package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.repository.CareNoteRepository;
import wd.team4.everycare.service.interfaces.MemberCareNoteService;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCareNoteServiceImpl implements MemberCareNoteService {

    private final CareNoteRepository careNoteRepository;

    @Override
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {
        return null;
    }
}
