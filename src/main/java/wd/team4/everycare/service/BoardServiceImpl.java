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
import wd.team4.everycare.dto.CommentFormDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.board.BoardInquiryDTO;
import wd.team4.everycare.dto.board.CommentDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.BoardRepository;
import wd.team4.everycare.repository.OrderProductRepository;
import wd.team4.everycare.repository.OrderRepository;
import wd.team4.everycare.repository.ProductRepository;
import wd.team4.everycare.service.interfaces.BoardService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final ProductRepository productRepository;
    private final BoardRepository boardRepository;
    private final FileStoreService fileStoreService;
    private final OrderProductRepository orderProductRepository;
    private final OrderRepository orderRepository;

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

    @Override
    public ResponseEntity<MyResponse> getInquiry(Long productId) {

        List<Board> getInquiry = boardRepository.findByCategoryAndProductId(BoardCategory.문의, productId);
        List<BoardDTO> inquiryDTO = new ArrayList<>();

        getInquiry.stream().map(board -> board.toBoardDTO()).forEach(inquiryDTO::add);

        /* TODO */
        for (BoardDTO boardDTO : inquiryDTO) {
            String yyyyMMdd = boardDTO.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            boardDTO.setCreatedAt(boardDTO.getCreatedAt());
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("문의글 조회")
                .body(inquiryDTO)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getDetailInquiry(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        BoardDTO boardDTO = board.toBoardDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("문의 상세")
                .body(boardDTO)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> updateInquiry(Long boardId, BoardDTO boardDTO) {
        Board board = boardRepository.findById(boardId).orElse(null);

        board.updateInfo(boardDTO);

        BoardDTO updateBoard = board.toBoardDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("문의글 수정")
                .body(updateBoard)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> removeInquiry(Long boardId) {
        boardRepository.deleteById(boardId);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("문의 삭제")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<MyResponse> getFAQ() {
        List<Board> FAQList = boardRepository.findByCategory(BoardCategory.FAQ);

        List<BoardDTO> FAQListDTO = new ArrayList<>();

        FAQList.stream().map(board -> board.toBoardDTO()).forEach(FAQListDTO::add);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("FAQ조회")
                .body(FAQListDTO)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getDetailFAQ(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        BoardDTO boardDTO = board.toBoardDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("FAQ상세조회")
                .body(boardDTO)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getComment(Long productId) {
        List<Board> all = boardRepository.findByProductIdAndCategory(productId, BoardCategory.후기);
        List<CommentDTO> commentDTOs = new ArrayList<>();

        all.stream().map(board -> board.toCommentDTO()).forEach(commentDTOs::add);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("후기 조회")
                .body(commentDTOs)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getDetailComment(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        board.updateCount();
        CommentDTO commentDTO = board.toCommentDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("후기 상세 조회")
                .body(commentDTO)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    //변경 필요
    @Override
    public ResponseEntity<MyResponse> createComment(PrincipalDetails principalDetails, CommentFormDTO commentFormDTO) throws IOException {

        Member member = principalDetails.getUser();

        UploadFile uploadFile = fileStoreService.storeFile(commentFormDTO.getAttachFile());
        String storeFileName = uploadFile.getStoreFileName();

        Long productId = commentFormDTO.getProductId();

        Product product = productRepository.findById(productId).orElse(null);

        if (commentFormDTO.getAttachFile() != null) {

            Board board = Board.builder()
                    .title(commentFormDTO.getTitle())
                    .content(commentFormDTO.getContent())
                    .category(BoardCategory.후기)
                    .createdAt(LocalDateTime.now())
                    .count(0)
                    .rating(commentFormDTO.getRating())
                    .fileName(storeFileName)
                    .member(member)
                    .product(product)
                    .build();
            boardRepository.save(board);
        } else {
            Board board = Board.builder()
                    .title(commentFormDTO.getTitle())
                    .content(commentFormDTO.getContent())
                    .category(BoardCategory.후기)
                    .createdAt(LocalDateTime.now())
                    .count(0)
                    .rating(commentFormDTO.getRating())
                    .member(member)
                    .product(product)
                    .build();
            boardRepository.save(board);
        }


        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("후기 작성")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> updateComment(Long boardId, BoardDTO boardDTO) {
        Board board = boardRepository.findById(boardId).orElse(null);
        board.updateInfo(boardDTO);

        CommentDTO commentDTO = board.toCommentDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("후기 업데이트")
                .body(commentDTO)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    //변경 필요
    @Override
    public ResponseEntity<MyResponse> removeComment(Long boardId) {
        boardRepository.deleteById(boardId);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("후기 삭제")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
