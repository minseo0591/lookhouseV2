package com.look.house.repository;


import com.look.house.domain.Board;
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

    List<Board> findAll();
    //페이징
    int countAll();
    List<Board> findPageRecord(@Param("firstIndex") int firstIndex, @Param("recordCountPerPage")int recordCountPerPage);
  
    void boardCommentCount(Long id);

    void updateCommentCount(@Param("id") Long id,@Param("type") String type);

}
