package wd.team4.everycare.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wd.team4.everycare.dto.ImageDTO;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_target_image_seq_generator",
        sequenceName = "care_target_image_seq",
        initialValue = 1, allocationSize = 1)
public class CareTargetImage {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_target_image_seq_generator")
    @Column(name = "care_target_image_id")
    private Long id;

    @Column(name = "care_target_image_upload_file_name")
    private String uploadFileName;

    @Column(name = "care_target_image_store_file_name")
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_id")
    private CareTarget careTarget;

    @Builder
    public CareTargetImage(String uploadFileName, String storeFileName, CareTarget careTarget) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.careTarget = careTarget;
    }

    public ImageDTO toImageDTO(){
        return ImageDTO.builder()
                .id(this.id)
                .storeFileName(this.storeFileName)
                .uploadFileName(this.uploadFileName)
                .build();
    }
}
