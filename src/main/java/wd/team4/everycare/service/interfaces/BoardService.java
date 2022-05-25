package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.board.BoardInquiryDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.io.IOException;

public interface BoardService {

    //문의
    ResponseEntity<MyResponse> inquiry(BoardInquiryDTO boardInquiryDTO, PrincipalDetails principalDetails, Long productId) throws IOException;
    ResponseEntity<MyResponse> getInquiry(Long productId);
    ResponseEntity<MyResponse> updateInquiry(Long boardId, BoardDTO boardDTO);
    ResponseEntity<MyResponse> removeInquiry(Long boardId);

    //FAQ 조회
    ResponseEntity<MyResponse> getFAQ();
}
