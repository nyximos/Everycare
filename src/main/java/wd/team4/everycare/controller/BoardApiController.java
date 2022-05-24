package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.board.BoardInquiryDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.BoardServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardServiceImpl boardService;

    @PostMapping("/store/products/{productId}/qna/new")
    public ResponseEntity<MyResponse> inquiry(@PathVariable("productId") Long productId, @AuthenticationPrincipal PrincipalDetails principalDetails,
                                              @ModelAttribute BoardInquiryDTO boardInquiryDTO) throws IOException {
        ResponseEntity<MyResponse> inquiry = boardService.inquiry(boardInquiryDTO, principalDetails, productId);
        return inquiry;
    }
}
