package com.sparta.collabobo.comment.service;

import com.sparta.collabobo.card.entity.Card;
import com.sparta.collabobo.card.repository.CardRepository;
import com.sparta.collabobo.comment.entity.Comment;
import com.sparta.collabobo.comment.repository.CommentRepository;
import com.sparta.collabobo.comment.requestDto.CommentRequest;
import com.sparta.collabobo.entity.User;
import com.sparta.collabobo.entity.UserRoleEnum;
import com.sparta.collabobo.jwt.JwtUtil;
import com.sparta.collabobo.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public CommentService(CommentRepository commentRepository, CardRepository cardRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.commentRepository = commentRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Comment createComment(Long cardId, CommentRequest commentDto, String token) {
        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new SecurityException("유효하지 않은 토큰입니다.");
        }

        // 토큰에서 사용자 정보 추출
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String username = claims.getSubject();
        UserRoleEnum role = UserRoleEnum.valueOf((String) claims.get(JwtUtil.AUTHORIZATION_KEY));

        // userRepository에서 username으로 사용자 정보 가져오기
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // Card와 Comment 객체를 생성
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new RuntimeException("카드를 찾을 수 없습니다."));

        Comment comment = new Comment(user, commentDto.getContent(), card);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByCardId(Long cardId) {
        return commentRepository.findByCard_Id(cardId);
    }

}
