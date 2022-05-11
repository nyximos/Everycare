package wd.team4.everycare.dto.board;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.MemberRole;
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
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int count;
    private MultipartFile attachFile;
    private String fileName;
    private String filePath;
    private MemberInfoDTO memberInfoDTO;

    public Board toBoard(){
        return Board.builder()
                .id(this.id)
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
