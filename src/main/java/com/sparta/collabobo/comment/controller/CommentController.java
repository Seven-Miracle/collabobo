package com.sparta.collabobo.comment.controller;


import com.sparta.collabobo.comment.entity.Comment;
import com.sparta.collabobo.comment.requestDto.CommentRequest;
import com.sparta.collabobo.comment.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cards")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{cardId}/comments")
    public Comment createComment(@PathVariable Long cardId, @RequestBody CommentRequest commentDto) {
        return commentService.createComment(cardId, commentDto);
    }

    @GetMapping("/{cardId}/comments")
    public List<Comment> getCommentsByCardId(@PathVariable Long cardId) {
        return commentService.getCommentsByCardId(cardId);
    }
}
