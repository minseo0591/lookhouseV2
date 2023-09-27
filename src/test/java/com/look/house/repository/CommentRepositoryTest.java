package com.look.house.repository;

import com.look.house.domain.Comment;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommentRepositoryTest.class);
    @Test
    void commentFindOne() {
        Optional<Comment> comment = commentRepository.commentFindOne(1L);
        logger.info("테스트 중 ={}",comment);
    }

    @Test
    void CommentFindAll(){
        List<Comment> comments = commentRepository.commentFindAll(1L);
        logger.info("List 테스트 중 ={}",comments);
    }
}