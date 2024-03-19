package com.sparta.collabobo.comment.service;

import com.sparta.collabobo.card.entity.Card;
import com.sparta.collabobo.card.repository.CardRepository;
import com.sparta.collabobo.comment.entity.Comment;
import com.sparta.collabobo.comment.repository.CommentRepository;
import com.sparta.collabobo.comment.requestDto.CommentRequest;
import com.sparta.collabobo.user.entity.User;
import com.sparta.collabobo.user.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CardRepository cardRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(Long cardId, CommentRequest commentDto) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("카드를 찾을 수 없습니다."));
        User user = userRepository.findById(commentDto.getUserId()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Comment comment = new Comment(user, commentDto.getContent(), card);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByCardId(Long cardId) {
        return commentRepository.findByCard_CardId(cardId);
    }

}
