package com.look.house.repository;


import com.look.house.domain.Board;
import com.look.house.domain.dto.RequestPageDTO;
import com.look.house.domain.dto.SearchDTO;
import com.look.house.domain.paging.Pagination;
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
    int countAll(SearchDTO searchDTO);
    List<Board> findAll1(@Param("searchDTO")SearchDTO searchDTO, @Param("pagination") Pagination pagination);
  
    void boardCommentCount(Long id);

    void updateCommentCount(@Param("id") Long id,@Param("type") String type);

    void updateHeartCount(@Param("id") Long id, @Param("type") String type);

    List<Board> findMyBoard(String writer);

}
