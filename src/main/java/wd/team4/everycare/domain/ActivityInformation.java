package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "activity_information")
@SequenceGenerator(name = "activity_information_seq_generator",
        sequenceName = "activity_information_seq",
        initialValue = 1, allocationSize = 1)
public class ActivityInformation {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_information_seq_generator")
    @Column(name = "activity_information_id", nullable = false)
    private Long id;

    @Column(name = "activity_information_start_time", length = 4, nullable = false)
    private String startTime;

    @Column(name = "activity_information_end_time", length = 4, nullable = false)
    private String endTime;

    @Column(name = "activity_information_content", length = 255)
    private String content;

    @Column(name = "activity_information_file_name", length = 255)
    private String fileName;

    @Column(name = "activity_information_file_path", length = 255)
    private String filePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_information_unit", nullable = false)
    private ActivityUnit activityUnit;

}
