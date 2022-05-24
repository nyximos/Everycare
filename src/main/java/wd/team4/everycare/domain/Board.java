package wd.team4.everycare.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.board.BoardInquiryDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "board_category", length = 10, nullable = false)
    private BoardCategory category;

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

    @Builder
    public Board(Long id, String title, String content, BoardCategory category, LocalDateTime createdAt,
                 LocalDateTime updatedAt, int count, String fileName, String filePath, Member member, Product product) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.count = count;
        this.fileName = fileName;
        this.filePath = filePath;
        this.member = member;
        this.product = product;
    }

    public BoardDTO toBoardDTO(){
        return BoardDTO.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .count(this.count)
                .fileName(this.fileName)
                .filePath(this.filePath)
                .memberInfoDTO(this.member.toMemberInfoDTO())
                .build();
    }

    public void updateInfo(BoardDTO boardDTO){
        if(StringUtils.isNotBlank(boardDTO.getTitle())) this.title=boardDTO.getTitle();
        if(StringUtils.isNotBlank(boardDTO.getContent())) this.content=boardDTO.getContent();
    }

    public BoardInquiryDTO boardInquiry(){
        return BoardInquiryDTO.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .count(this.count)
                .fileName(this.fileName)
                .filePath(this.filePath)
                .member(this.member)
                .build();
    }

}
