package com.look.house.repository;


import com.look.house.domain.Board;
import com.look.house.domain.dto.MyPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageRepository {


    List<MyPageDTO.MyPageResponse> findMyCommentAndBoard(String writer);


    List<Board> findMyHeartBoard(String userId);
}
