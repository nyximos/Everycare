package wd.team4.everycare.domain;

import lombok.*;
import wd.team4.everycare.dto.careSitter.CareSitterImageDTO;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "care_sitter_image_seq_generator",
        sequenceName = "care_sitter_image_seq",
        initialValue = 1, allocationSize = 1)
public class CareSitterImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_image_seq_generator")
    @Column(name = "care_sitter_image_id", nullable = false)
    private Long id;

    @Column(name = "care_sitter_image_upload_file_name")
    private String uploadFileName;

    @Column(name = "care_sitter_image_store_file_name")
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @Builder
    public CareSitterImage(String uploadFileName, String storeFileName, CareSitter careSitter) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.careSitter = careSitter;
    }

    public CareSitterImageDTO toCareSitterImageDTO(){
        return CareSitterImageDTO.builder()
                .storeFileName(this.storeFileName)
                .uploadFileName(this.uploadFileName)
                .build();
    }

}
