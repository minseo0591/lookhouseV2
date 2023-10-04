package com.look.house.repository;

import com.look.house.domain.Board;
import com.look.house.domain.dto.MyPageDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyPageRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryTest.class);

    @Autowired
    private MyPageRepository myPageRepository;

    @Test
    void findMyCommentAndBoard() {
        List<MyPageDTO.MyPageResponse> commentAndBoard = myPageRepository.findMyCommentAndBoard("정성진");
        logger.info("commentAndBoard={}",commentAndBoard);

    }
    @Test
    @DisplayName("좋아요 게시글")
    void findMyHeartBoard(){
        List<Board> myHeartBoard = myPageRepository.findMyHeartBoard("정성진");
        logger.info("myHeartBoard ={}",myHeartBoard);
    }
}