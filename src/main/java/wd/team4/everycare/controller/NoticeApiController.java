package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.NoticeServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoticeApiController {

    private final NoticeServiceImpl noticeService;

    @GetMapping("/notice")
    public ResponseEntity<MyResponse> getAllNotice(){
        ResponseEntity<MyResponse> allNotice = noticeService.findAllNotice();
        return allNotice;
    }

    @PostMapping("/admin/notice")
    public ResponseEntity<MyResponse> saveNotice(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute BoardDTO boardDTO) throws IOException {
        ResponseEntity<MyResponse> saveNotice = noticeService.create(principalDetails, boardDTO);
        return saveNotice;
    }

    @GetMapping("/admin/notice/{id}")
    public ResponseEntity<MyResponse> detailNotice(@PathVariable("id")Long id){
        ResponseEntity<MyResponse> detailNotice = noticeService.findNotice(id);

        return detailNotice;
    }

    @DeleteMapping("/admin/notice/{id}")
    public ResponseEntity<MyResponse> deleteNotice(@PathVariable("id")Long id){
        ResponseEntity<MyResponse> deleteNotice = noticeService.delete(id);
        return deleteNotice;
    }

    @PatchMapping("/admin/notice/{id}")
    public ResponseEntity<MyResponse> updateNotice(@PathVariable("id") Long id,
                                                   @ModelAttribute BoardDTO boardDTO){

        System.out.println("Aaaaaaaaa");
        ResponseEntity<MyResponse> update = noticeService.update(id, boardDTO);
        return update;
    }
}
