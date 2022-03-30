package wd.team4.everycare.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
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

    @ManyToOne
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @Builder
    public CareSitterImage(String uploadFileName, String storeFileName, CareSitter careSitter) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.careSitter = careSitter;
    }
}
