package com.look.house.service;

import com.look.house.auth.PrincipalDetails;
import com.look.house.domain.Board;
import com.look.house.domain.Comment;
import com.look.house.domain.Member;
import com.look.house.domain.dto.CommentDTO;
import com.look.house.repository.BoardRepository;
import com.look.house.repository.CommentRepository;
import com.look.house.util.error.ErrorCode;
import com.look.house.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void saveComment(Long boardId, PrincipalDetails principalDetails, CommentDTO.Write commentDTO){
        if(principalDetails == null){
            throw new CustomException(ErrorCode.NOT_MEMBER);
        }
        Board board = boardRepository.findOne(boardId).orElseThrow(() ->
                new CustomException(ErrorCode.ID_NOT_FOUND)
        );
        Comment comment = Comment.builder()
                .boardId(board.getId())
                .content(commentDTO.getContent())
                .writer(principalDetails.getMember().getNickName())
                .createTime(LocalDateTime.now())
                .build();
        commentRepository.commentSave(comment);
        boardRepository.boardCommentCount(board.getId());
    }


    public CommentDTO.ResponseList listComment(Long boardId){
        List<Comment> comments = commentRepository.commentFindAll(boardId);
        List<CommentDTO.Response> comment = CommentDTO.Response.ListCommentTOCommentDto(comments);
        CommentDTO.ResponseList responseList = new CommentDTO.ResponseList(comment, comment.size());

        return responseList;
    }




}
