package wd.team4.everycare.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentFormDTO {
    private String title;
    private String content;
    private int rating;
    private Long productId;
    private MultipartFile attachFile;
}
