package com.look.house.repository;

import com.look.house.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


@Mapper
public interface CommentRepository {

    void commentSave(Comment comment);


    Optional<Comment> commentFindOne(Long commentId);

    List<Comment> commentFindAll(Long boardId);

    void commentUpdate(Comment comment);

    void commentDelete(Long commentId);
}
