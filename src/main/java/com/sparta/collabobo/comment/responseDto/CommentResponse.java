package com.sparta.collabobo.comment.responseDto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponse {
    private String content;
    private LocalDateTime createdAt;

    public CommentResponse(String content, LocalDateTime createdAt) {
        this.content = content;
        this.createdAt = createdAt;
    }
}
