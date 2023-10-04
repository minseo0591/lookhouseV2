package com.look.house.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MyPageServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);

    @Autowired
    private MyPageService myPageService;
    @Test
    @DisplayName("좋아요한 게시글 true")
    void myPageHeartList() {

    }
}