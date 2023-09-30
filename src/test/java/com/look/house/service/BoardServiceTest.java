package com.look.house.service;

import com.look.house.domain.dto.BoardDTO;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);

    @Test
    void list() {
        List<BoardDTO.Response> list = boardService.list();

        logger.info("boardList={}",list);
    }
}