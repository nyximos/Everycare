package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.BoardCategory;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class InitBoardService {

    private final EntityManager em;
    private final MemberRepository memberRepository;

    public void dbInit() {
        Member admin = memberRepository.findById("admin").get();

        Board board1 = board("title1", "content1", BoardCategory.공지, LocalDateTime.now(), LocalDateTime.now(), 1, "fileName1", "filePath1", admin);
        Board board2 = board("title2", "content2", BoardCategory.공지, LocalDateTime.now(), LocalDateTime.now(), 2, "fileName2", "filePath2", admin);
        Board board3 = board("title3", "content3", BoardCategory.후기, LocalDateTime.now(), LocalDateTime.now(), 3, "fileName3", "filePath3", admin);
        Board board4 = board("title4", "content4", BoardCategory.후기, LocalDateTime.now(), LocalDateTime.now(), 4, "fileName4", "filePath4", admin);
        Board board5 = board("title5", "content5", BoardCategory.문의, LocalDateTime.now(), LocalDateTime.now(), 5, "fileName5", "filePath5", admin);
        Board board6 = board("title6", "content6", BoardCategory.문의, LocalDateTime.now(), LocalDateTime.now(), 6, "fileName6", "filePath6", admin);

    }

    private Board board(String title, String content, BoardCategory category, LocalDateTime createdAt, LocalDateTime updatedAt, int count, String fileName, String filePath, Member member){
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


}
