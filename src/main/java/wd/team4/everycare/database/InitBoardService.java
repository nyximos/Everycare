package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.BoardCategory;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.ProductRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class InitBoardService {

    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public void dbInit() {
        Member admin = memberRepository.findById("admin").get();
        Product product = productRepository.findById(1L).get();

        Board board1 = board("title1", "content1", BoardCategory.공지, LocalDateTime.now(), LocalDateTime.now(), 1, "fileName1", "filePath1", admin);
        Board board2 = board("title2", "content2", BoardCategory.공지, LocalDateTime.now(), LocalDateTime.now(), 2, "fileName2", "filePath2", admin);
        Board board3 = board("title3", "content3", BoardCategory.공지, LocalDateTime.now(), LocalDateTime.now(), 3, "fileName3", "filePath3", admin);
        Board board4 = board("title4", "content4", BoardCategory.공지, LocalDateTime.now(), LocalDateTime.now(), 4, "fileName4", "filePath4", admin);

        Board board5 = board("title5", "content5", BoardCategory.문의, LocalDateTime.now(), LocalDateTime.now(), 5, "fileName5", "filePath5", admin);
        Board board6 = board("title6", "content6", BoardCategory.문의, LocalDateTime.now(), LocalDateTime.now(), 6, "fileName6", "filePath6", admin);
        Board board7 = board("title7", "content7", BoardCategory.문의, LocalDateTime.now(), LocalDateTime.now(), 7, "fileName7", "filePath7", admin);
        Board board8 = board("title8", "content8", BoardCategory.문의, LocalDateTime.now(), LocalDateTime.now(), 8, "fileName8", "filePath8", admin);

        Board board9 = commentBoard("title9", "content9", BoardCategory.후기, LocalDateTime.now(), LocalDateTime.now(), 9, 5, "fileName9", "filePath9", admin, product);
        Board board10 = commentBoard("title10", "content10", BoardCategory.후기, LocalDateTime.now(), LocalDateTime.now(), 10, 5, "fileName10", "filePath10", admin, product);
        Board board11 = commentBoard("title11", "content11", BoardCategory.후기, LocalDateTime.now(), LocalDateTime.now(), 11, 5, "fileName11", "filePath11", admin, product);
        Board board12 = commentBoard("title12", "content12", BoardCategory.후기, LocalDateTime.now(), LocalDateTime.now(), 12, 5, "fileName12", "filePath12", admin, product);

        Board board13 = inquiryBoard("title13", "content13", BoardCategory.FAQ, LocalDateTime.now(), LocalDateTime.now(), 13, "fileName13", "filePath13", admin, product);
        Board board14 = inquiryBoard("title14", "content14", BoardCategory.FAQ, LocalDateTime.now(), LocalDateTime.now(), 14, "fileName14", "filePath14", admin, product);
        Board board15 = inquiryBoard("title15", "content15", BoardCategory.FAQ, LocalDateTime.now(), LocalDateTime.now(), 15, "fileName15", "filePath15", admin, product);
        Board board16 = inquiryBoard("title16", "content16", BoardCategory.FAQ, LocalDateTime.now(), LocalDateTime.now(), 16, "fileName16", "filePath16", admin, product);

    }

    private Board board(String title, String content, BoardCategory category, LocalDateTime createdAt, LocalDateTime updatedAt, int count, String fileName, String filePath, Member member) {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .count(count)
                .fileName(fileName)
                .filePath(filePath)
                .member(member)
                .build();
        em.persist(board);

        return board;
    }

    private Board inquiryBoard(String title, String content, BoardCategory category, LocalDateTime createdAt, LocalDateTime updatedAt, int count, String fileName, String filePath, Member member, Product product) {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .count(count)
                .fileName(fileName)
                .filePath(filePath)
                .member(member)
                .product(product)
                .build();
        em.persist(board);

        return board;
    }

    private Board commentBoard(String title, String content, BoardCategory category, LocalDateTime createdAt, LocalDateTime updatedAt, int count, int rating, String fileName, String filePath, Member member, Product product) {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .count(count)
                .rating(rating)
                .fileName(fileName)
                .filePath(filePath)
                .member(member)
                .product(product)
                .build();
        em.persist(board);

        return board;
    }

}
