package com.sparta.collabobo.board.dto.responsedto;

import com.sparta.collabobo.board.entity.BoardEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardResponseDto {
    private Long id;
    private String title;
    private String description;
    private String color;
    private boolean deleted;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    
    // 엔티티를 받아서 BoardResponseDto를 생성함
    public static BoardResponseDto from(BoardEntity boardEntity) {
        return BoardResponseDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .description(boardEntity.getDescription())
                .color(boardEntity.getColor())
                .deleted(boardEntity.isDeleted())
                .createdDate(boardEntity.getCreatedDate())
                .updatedDate(boardEntity.getUpdatedDate())
                .deletedDate(boardEntity.getDeletedDate())
                .build();
    }
}