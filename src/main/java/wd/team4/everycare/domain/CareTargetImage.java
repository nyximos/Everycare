package wd.team4.everycare.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
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
}
