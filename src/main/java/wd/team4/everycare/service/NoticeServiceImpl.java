package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.BoardCategory;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.BoardRepository;
import wd.team4.everycare.service.interfaces.NoticeService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final BoardRepository boardRepository;
    private final FileStoreService fileStoreService;

    @Override
    public ResponseEntity<MyResponse> findAllNotice() {
        List<Board> allNotice = boardRepository.findByCategory(BoardCategory.공지);
        List<BoardDTO> boardDTOs = new ArrayList<>();
        allNotice.stream().map(board -> board.toBoardDTO()).forEach(boardDTOs::add);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .body(boardDTOs)
                .message("전체 조회")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails, BoardDTO boardDTO) throws IOException {

        UploadFile uploadFile = fileStoreService.storeFile(boardDTO.getAttachFile());
        String uploadFileName = uploadFile.getUploadFileName();
        String storeFileName = uploadFile.getStoreFileName();;

        LocalDateTime now = LocalDateTime.now();

        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .category(BoardCategory.공지)
                .createdAt(now)
                .updatedAt(now)
                .count(0)
                .filePath(uploadFileName)
                .fileName(storeFileName)
                .member(principalDetails.getUser())
                .build();
        System.out.println("board = " + board);
        boardRepository.save(board);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .body(board)
                .message("전체 조회")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> update(Long id,BoardDTO boardDTO) {
        Board board = boardRepository.findById(id).get();
        board.updateInfo(boardDTO);

        BoardDTO updateBoard = board.toBoardDTO();
        System.out.println("updateBoard = " + updateBoard);
        MyResponse body = MyResponse.builder()
                .message("수정")
                .header(StatusEnum.OK)
                .body(updateBoard)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> delete(Long id) {
        boardRepository.deleteById(id);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("삭제 성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> findNotice(Long id) {
        Optional<Board> detailNotice = boardRepository.findById(id);
        Board detailBoard = detailNotice.get();
        detailBoard.updateCount();
        BoardDTO boardDTO = detailBoard.toBoardDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("상세조회 성공")
                .body(boardDTO)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
