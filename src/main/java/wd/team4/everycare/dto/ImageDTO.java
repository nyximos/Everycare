package wd.team4.everycare.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageDTO {

    private Long id;
    private String uploadFileName;
    private String storeFileName;

}
