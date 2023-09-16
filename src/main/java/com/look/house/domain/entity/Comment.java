package com.look.house.domain.entity;

import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long cmId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column(name = "comment_content")
    private String cmContent;

    @Column(name = "comment_date", insertable = false)
    private Date cmDate;
}
