package wd.team4.everycare.dto.caretarget;

import lombok.*;
import wd.team4.everycare.dto.ImageDTO;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareTargetListViewDTO {

    private Long id;
    private String name;
    private ImageDTO imageDTO;
}
