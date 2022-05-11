package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.BoardRepository;
import wd.team4.everycare.service.interfaces.NoticeService;

import javax.transaction.Transactional;
import java.io.IOException;
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
        List<Board> allNotice = boardRepository.findAll();
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

        Board board = Board.builder()
                .id(boardDTO.getId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .category(boardDTO.getCategory())
                .createdAt(boardDTO.getCreatedAt())
                .updatedAt(boardDTO.getUpdatedAt())
                .count(boardDTO.getCount())
                .filePath(uploadFileName)
                .fileName(storeFileName)
                .member(principalDetails.getUser())
                .build();


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
        BoardDTO toBoardDTO = board.toBoardDTO();
        board.updateInfo(toBoardDTO);
        return null;
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
        BoardDTO boardDTO = detailBoard.toBoardDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("상세조회 성공")
                .body(boardDTO)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
