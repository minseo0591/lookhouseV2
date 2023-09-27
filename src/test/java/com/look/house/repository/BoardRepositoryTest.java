package com.look.house.repository;

import com.look.house.domain.Board;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryTest.class);
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void findAll() {
        List<Board> all = boardRepository.findAll();
        logger.info("전부다 ={}",all);
    }
}