package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.board.BoardInquiryDTO;
import wd.team4.everycare.dto.board.CommentDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.BoardServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardServiceImpl boardService;

    @GetMapping("/store/products/{productId}/qna")
    public ResponseEntity<MyResponse> getInquiry(@PathVariable("productId") Long productId){
        ResponseEntity<MyResponse> getInquiry = boardService.getInquiry(productId);
        return getInquiry;
    }

    @GetMapping("/store/products/qna/{boardId}")
    public ResponseEntity<MyResponse> getDetailInquiry(@PathVariable("boardId") Long boardId){
        ResponseEntity<MyResponse> getDetail = boardService.getDetailInquiry(boardId);
        return getDetail;
    }

    @PatchMapping("/store/products/qna/{boardId}")
    public ResponseEntity<MyResponse> updateInquiry(@PathVariable("boardId") Long boardId, @ModelAttribute BoardDTO boardDTO){
        ResponseEntity<MyResponse> updateInquiry = boardService.updateInquiry(boardId, boardDTO);
        return updateInquiry;
    }

    @PostMapping("/store/products/{productId}/qna/new")
    public ResponseEntity<MyResponse> inquiry(@PathVariable("productId") Long productId, @AuthenticationPrincipal PrincipalDetails principalDetails,
                                              @ModelAttribute BoardInquiryDTO boardInquiryDTO) throws IOException {
        ResponseEntity<MyResponse> inquiry = boardService.inquiry(boardInquiryDTO, principalDetails, productId);
        return inquiry;
    }

    @DeleteMapping("/store/products/qna/{boardId}")
    public ResponseEntity<MyResponse> removeInquiry(@PathVariable("boardId") Long boardId){
        ResponseEntity<MyResponse> remove = boardService.removeInquiry(boardId);
        return remove;
    }

    @GetMapping("/faq")
    public ResponseEntity<MyResponse> getFAQ(){
        ResponseEntity<MyResponse> faq = boardService.getFAQ();
        return faq;
    }

    @GetMapping("/store/products/reviews/{boardId}")
    public ResponseEntity<MyResponse> getReviews(@PathVariable("boardId")Long boardId){
        ResponseEntity<MyResponse> review = boardService.getComment(boardId);
        return review;
    }

    @GetMapping("/store/products/review/{boardId}")
    public ResponseEntity<MyResponse> getDetailReview(@PathVariable("boardId")Long boardId){
        ResponseEntity<MyResponse> detailReview = boardService.getDetailComment(boardId);
        return detailReview;
    }

    @PostMapping("/dashboard/orders/products")
    public ResponseEntity<MyResponse> createReview(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute CommentDTO commentDTO) throws IOException {
        ResponseEntity<MyResponse> review = boardService.createComment(principalDetails, commentDTO);
        return review;
    }

    @PatchMapping("dashboard/orders/products/{boardId}")
    public ResponseEntity<MyResponse> updateReview(@PathVariable("boardId") Long boardId, @ModelAttribute BoardDTO boardDTO){
        ResponseEntity<MyResponse> updateReview = boardService.updateComment(boardId, boardDTO);
        return updateReview;
    }

    @DeleteMapping("/dashboard/orders/products/{boardId}")
    public ResponseEntity<MyResponse> removeReview(@PathVariable("boardId") Long boardId){
        ResponseEntity<MyResponse> removeReview = boardService.removeComment(boardId);
        return removeReview;
    }
}
