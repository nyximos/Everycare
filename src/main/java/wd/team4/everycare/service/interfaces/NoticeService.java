package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.io.IOException;

public interface NoticeService {
    ResponseEntity<MyResponse> findAllNotice();
    ResponseEntity<MyResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails, BoardDTO boardDTO) throws IOException;
    ResponseEntity<MyResponse> update(Long id, BoardDTO boardDTO);
    ResponseEntity<MyResponse> delete(Long id);
    ResponseEntity<MyResponse> findNotice(Long id);
}
