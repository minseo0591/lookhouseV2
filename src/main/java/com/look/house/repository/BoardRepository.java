package com.look.house.repository;


import com.look.house.domain.Board;
import com.look.house.domain.dto.SearchDTO;
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
    int countAll(SearchDTO sdt);
    List<Board> findAll1(SearchDTO searchDTO);
  
    void boardCommentCount(Long id);

    void updateCommentCount(@Param("id") Long id,@Param("type") String type);

}
