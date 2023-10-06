package com.look.house.repository;


import com.look.house.domain.Board;
import com.look.house.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardRepository {
    void boardSave(Board board);

    Optional<Board> findOne(Long id);

    void boardUpdate(@Param("id") Long id, @Param("board") Board board);

    void boardDelete(Long id);

    void updateCommentCount(@Param("id") Long id,@Param("type") String type);

    void updateHeartCount(@Param("id") Long id, @Param("type") String type);

    List<Board> findMyBoard(String writer);

    /*현재 리스트 가져오는 페이징 적용*/
    List<Board> findList(Criteria criteria);

    int findCount(Criteria criteria);






}
