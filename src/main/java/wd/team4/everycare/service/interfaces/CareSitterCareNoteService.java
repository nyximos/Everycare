package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.careNote.CareNoteImageDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.io.IOException;

public interface CareSitterCareNoteService {
    ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> get(Long id);

    ResponseEntity<MyResponse> start(Long id, CareNoteImageDTO imageDTO) throws IOException;

    ResponseEntity<MyResponse> getSchedule(Long id);

    ResponseEntity<MyResponse> saveContent(Long id, String content);

    ResponseEntity<MyResponse> savePhoto(Long id, CareNoteImageDTO imageDTO) throws IOException;

    ResponseEntity<MyResponse> removeContent(Long id);

    ResponseEntity<MyResponse> removePhoto(Long id);

    ResponseEntity<MyResponse> complete(Long id);

    ResponseEntity<MyResponse> getContracts(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> getCareNotes(Long id);

    ResponseEntity<MyResponse> getCareNote(Long id);
}
