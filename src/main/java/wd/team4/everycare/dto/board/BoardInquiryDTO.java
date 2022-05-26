package wd.team4.everycare.dto.board;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.BoardCategory;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Product;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardInquiryDTO {

    private Long id;
    private String title;
    private String content;
    private BoardCategory category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int count;
    private MultipartFile attachFile;
    private String fileName;
    private String filePath;
    private Member member;
    private Product product;

    public Board toBoard(){
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .count(this.count)
                .fileName(this.fileName)
                .filePath(this.filePath)
                .build();
    }
}
