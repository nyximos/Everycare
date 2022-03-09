package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "board_seq_generator",
        sequenceName = "board_seq",
        initialValue = 1, allocationSize = 1)
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq")
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title", nullable = false)
    private String title;

    @Column(name = "board_content", length = 4000, nullable = false)
    private String content;

    @Column(name = "board_category", length = 10, nullable = false)
    private String category;

    @Column(name = "board_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "board_updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime updatedAt;

    @Column(name = "board_count", nullable = false)
    private int count;

    @Column(name = "board_file_name")
    private String fileName;

    @Column(name = "board_file_path")
    private String filePath;
    /**
     * TODO 회원, 상품 외래키 연결
     * */
}
