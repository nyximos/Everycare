package wd.team4.everycare.dto.board;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.BoardCategory;
import wd.team4.everycare.dto.member.MemberNameDTO;
import wd.team4.everycare.dto.product.ProductCategoryDTO;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String title;
    private String content;
    private BoardCategory category;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSS")
    private LocalDateTime updatedAt;
    private int count;
    private MultipartFile attachFile;
    private String fileName;
    private String filePath;
    private MemberNameDTO member;
    private ProductCategoryDTO product;
}
