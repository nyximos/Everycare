package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.BoardCategory;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.board.BoardInquiryDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.BoardRepository;
import wd.team4.everycare.repository.ProductRepository;
import wd.team4.everycare.service.interfaces.BoardService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final ProductRepository productRepository;
    private final BoardRepository boardRepository;
    private final FileStoreService fileStoreService;

    @Override
    public ResponseEntity<MyResponse> inquiry(BoardInquiryDTO boardInquiryDTO, PrincipalDetails principalDetails, Long productId) throws IOException {
        Member member = principalDetails.getUser();
        Product product = productRepository.findById(productId).orElse(null);

        UploadFile uploadFile = fileStoreService.storeFile(boardInquiryDTO.getAttachFile());
        String uploadFileName = uploadFile.getUploadFileName();
        String storeFileName = uploadFile.getStoreFileName();

        Board board = Board.builder()
                .title(boardInquiryDTO.getTitle())
                .content(boardInquiryDTO.getContent())
                .category(BoardCategory.문의)
                .createdAt(boardInquiryDTO.getCreatedAt())
                .updatedAt(boardInquiryDTO.getUpdatedAt())
                .count(0)
                .filePath(uploadFileName)
                .fileName(storeFileName)
                .member(member)
                .product(product)
                .build();

        Board saveBoard = boardRepository.save(board);
        BoardDTO boardDTO = saveBoard.toBoardDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("문의글 등록")
                .body(boardDTO)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
