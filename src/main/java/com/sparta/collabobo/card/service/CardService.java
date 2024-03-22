package com.sparta.collabobo.card.service;

import com.sparta.collabobo.board.entity.BoardEntity;
import com.sparta.collabobo.board.repository.BoardRepository;
import com.sparta.collabobo.card.entity.Card;
import com.sparta.collabobo.card.entity.CardColorEnum;
import com.sparta.collabobo.card.entity.CardStatusEnum;
import com.sparta.collabobo.card.repository.CardRepository;
import com.sparta.collabobo.card.requestDto.CardRequest;
import com.sparta.collabobo.card.requestDto.CardUpdateRequest;
import com.sparta.collabobo.card.requestDto.StatusUpdateRequest;
import com.sparta.collabobo.card.responseDto.CardResponse;
import com.sparta.collabobo.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final BoardRepository boardRepository;

    public void createCard(Long boardId, CardRequest request, User user) {

        BoardEntity board = findBoard(boardId);
        // 색상 예외처리
        if (CardColorEnum.isNotExistsColor(request.getColor())) {
            throw new IllegalArgumentException("잘못된 색상이 들어왔습니다.");
        }
        //컬럼 상태 예외처리
        if (CardStatusEnum.isNotExistsStatus(request.getStatus())) {
            throw new IllegalArgumentException("잘못된 상태 컬럼이 들어왔습니다.");
        }

        Card card = new Card(
            request.getTitle(),
            request.getContent(),
            request.getColor(),
            request.getStatus(),
            user,
            board);
        cardRepository.save(card);

    }

    public List<CardResponse> getCards(Long boardId, User user) {
        return null;
    }

    public void updateCard(Long cardId, CardUpdateRequest request, User user) {

    }

    public void deleteCard(
        Long boardId,
        Long cardId,
        User user
    ) {
        // todo : worker권한이 있는 사용자만 삭제할 수 있는 로직 추가로 넣어야 됨
        BoardEntity board = findBoard(boardId);
        Card card = findCard(cardId);
        card.softDelete(board, user);
    }

    public void ChangeCardStatus(Long cardId, StatusUpdateRequest request, User user) {
        Card card = findCard(cardId);
        // todo : worker권한이 있는 사용자만 삭제할 수 있는 로직 추가로 넣어야 됨
        //컬럼 상태 예외처리
        if (CardStatusEnum.isNotExistsStatus(request.getStatus())) {
            throw new IllegalArgumentException("잘못된 상태 컬럼이 들어왔습니다.");
        }
        card.updateCardStatus(request.getStatus());
    }

    private BoardEntity findBoard(Long boardId) {
        return boardRepository.findById(boardId)
            .orElseThrow(() -> new IllegalArgumentException("없는 게시글입니다."));
    }

    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("없는 카드입니다."));
    }

    // 중간테이블에서 카드아이디로 조회
    // 카드에 참여한 유저 아이디가 리스트형태로 옴

}
