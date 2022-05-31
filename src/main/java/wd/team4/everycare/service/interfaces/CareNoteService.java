package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.response.MyResponse;

public interface CareNoteService {

    ResponseEntity<MyResponse> getCareNotes(Long id);

    ResponseEntity<MyResponse> getCareNote(Long id);

}
