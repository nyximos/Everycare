package wd.team4.everycare.dto.store;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreAdminListDTO {
    private Long id;
    private String name;
    private String memberId;
}