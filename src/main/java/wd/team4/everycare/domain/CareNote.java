package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.UploadFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_note_seq_generator",
        sequenceName = "care_note_seq",
        initialValue = 1, allocationSize = 1)
public class CareNote {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_note_seq_generator")
    @Column(name = "care_note_id")
    private Long id;

    @Column(name = "care_note_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "care_note_start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;

    @Column(name = "care_note_end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    @Column(name = "care_note_upload_file_name")
    private String uploadFileName;

    @Column(name = "care_note_store_file_name")
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id", nullable = false)
    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void saveImage(UploadFile attachFile){
        this.uploadFileName = attachFile.getUploadFileName();
        this.storeFileName = attachFile.getStoreFileName();
        this.startTime = LocalDateTime.now();
    }
}
