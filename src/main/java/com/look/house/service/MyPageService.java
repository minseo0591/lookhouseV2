package com.look.house.service;


import com.look.house.auth.PrincipalDetails;
import com.look.house.domain.Board;
import com.look.house.domain.dto.BoardDTO;
import com.look.house.domain.dto.MyPageDTO;
import com.look.house.repository.MyPageRepository;
import com.look.house.util.error.ErrorCode;
import com.look.house.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MyPageRepository myPageRepository;
    private final HeartService heartService;

    public MyPageDTO.MyPageResponseList myPageResponseList(PrincipalDetails principalDetails){
        if(principalDetails == null){
            throw new CustomException(ErrorCode.NOT_MEMBER);
        }

        List<MyPageDTO.MyPageResponse> commentAndBoard =
                myPageRepository.findMyCommentAndBoard(principalDetails.getMember().getNickName());

        return new MyPageDTO.MyPageResponseList(commentAndBoard,commentAndBoard.size());
    }

    public BoardDTO.ResponseList myPageHeartList(PrincipalDetails principalDetails){
        if(principalDetails == null){
            throw new CustomException(ErrorCode.NOT_MEMBER);
        }
        List<Board> myHeartBoard = myPageRepository.findMyHeartBoard(principalDetails.getMember().getNickName());
        List<BoardDTO.Response> responses = BoardDTO.Response.ListBoardToBoardDto(myHeartBoard);
        for (BoardDTO.Response response : responses) {
            boolean heart = heartService.isHeart(response.getId(), principalDetails.getMember().getNickName());
            response.setHeartStatus(heart);
        }

        return new BoardDTO.ResponseList(responses, responses.size());

    }

}
