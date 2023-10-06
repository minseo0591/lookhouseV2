package com.look.house.repository;

import com.look.house.domain.Board;
import com.look.house.domain.Criteria;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class BoardRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryTest.class);
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void findAll() {
        Criteria criteria = new Criteria();
        criteria.setCateId(20001);
        criteria.setKeyword("19");
        criteria.setType("TCW");
        List<Board> list = boardRepository.findList(criteria);
        logger.info("list={}",list);

    }

//
//    @Test
//    void save(){
//
//        Board board = new Board(434L,"하이","내용입니다", LocalDateTime.now(),"관리자",0,0,10001);
//        boardRepository.boardSave(board);
//    }




}