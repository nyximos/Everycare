package wd.team4.everycare.dto.board;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.BoardCategory;
import wd.team4.everycare.dto.member.MemberInfoDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private int rating;
    private BoardCategory category;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSS")
    private LocalDateTime updatedAt;
    private int count;
    private MultipartFile attachFile;
    private String fileName;
    private String filePath;
    private MemberInfoDTO memberInfoDTO;

    public Board toBoard(){
        return Board.builder()
                .id(this.id)
                .rating(this.rating)
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .count(this.count)
                .filePath(this.filePath)
                .fileName(this.fileName)
                .member(memberInfoDTO.toMemberInfoDTO())
                .build();
    }
}
