package wd.team4.everycare.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "board_seq_generator",
        sequenceName = "board_seq",
        initialValue = 1, allocationSize = 1)
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_generator")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
