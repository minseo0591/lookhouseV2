package com.look.house.repository;


import com.look.house.domain.Heart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface HeartRepository {


    void heartSave(Heart heart);

    Optional<Heart> findByBoardIdAndMemberId(@Param("boardId")Long boardId, @Param("userId")String userId);

    void heartDelete(Heart heart);
}
