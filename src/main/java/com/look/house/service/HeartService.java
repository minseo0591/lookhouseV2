package com.look.house.service;


import com.look.house.auth.PrincipalDetails;
import com.look.house.domain.Board;
import com.look.house.domain.Heart;
import com.look.house.repository.BoardRepository;
import com.look.house.repository.HeartRepository;
import com.look.house.util.error.ErrorCode;
import com.look.house.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public boolean heartLike(Long boardId, PrincipalDetails principalDetails) {
        if (principalDetails == null) {
            throw new CustomException(ErrorCode.NOT_MEMBER);
        }
        Board board = boardRepository.findOne(boardId).orElseThrow(() ->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        if(heartRepository.findByBoardIdAndMemberId(board.getId(),principalDetails.getMember().getNickName()).isPresent()){
                throw new CustomException(ErrorCode.MEMBER_HEART_COUNT);
        }

        Heart heart = Heart.builder()
                .userId(principalDetails.getMember().getNickName())
                .boardId(board.getId())
                .build();
        boardRepository.updateHeartCount(board.getId(),"heartAdd");
        heartRepository.heartSave(heart);
//        return heart.getHeartId();
        return true;
    }

    @Transactional
    public boolean heartUn(Long boardId, PrincipalDetails principalDetails) {
        if (principalDetails == null) {
            throw new CustomException(ErrorCode.NOT_MEMBER);
        }
        Board board = boardRepository.findOne(boardId).orElseThrow(() ->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        heartRepository.findByBoardIdAndMemberId(board.getId(),principalDetails.getMember().getNickName()).orElseThrow(()->
                    new CustomException(ErrorCode.MEMBER_HEART_COUNT)
                );

        Heart heart = Heart.builder()
                .userId(principalDetails.getMember().getNickName())
                .boardId(board.getId())
                .build();
        boardRepository.updateHeartCount(board.getId(),"heartDelete");
        heartRepository.heartDelete(heart);

        return false;
    }

    public boolean isHeart(Long boardId, String userId) {
        Heart heart = heartRepository.findByBoardIdAndMemberId(boardId, userId).orElse(null);
        if (heart == null) {
            return false;
        }
        return true;
    }

}
