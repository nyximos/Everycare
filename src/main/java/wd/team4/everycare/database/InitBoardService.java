package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.repository.BoardRepository;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class InitBoardService {

    private final EntityManager em;
    private final MemberRepository memberRepository;

    public void dbInit() {
        Member admin = memberRepository.findById("admin").get();

        Board board1 = board("title1", "content1", "category1", LocalDateTime.now(), LocalDateTime.now(), 1, "fileName1", "filePath1", admin);
        Board board2 = board("title2", "content2", "category2", LocalDateTime.now(), LocalDateTime.now(), 2, "fileName2", "filePath2", admin);
        Board board3 = board("title3", "content3", "category3", LocalDateTime.now(), LocalDateTime.now(), 3, "fileName3", "filePath3", admin);
        Board board4 = board("title4", "content4", "category4", LocalDateTime.now(), LocalDateTime.now(), 4, "fileName4", "filePath4", admin);
        Board board5 = board("title5", "content5", "category5", LocalDateTime.now(), LocalDateTime.now(), 5, "fileName5", "filePath5", admin);

    }

    private Board board(String title, String content, String category, LocalDateTime createdAt, LocalDateTime updatedAt, int count, String fileName, String filePath, Member member){
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
