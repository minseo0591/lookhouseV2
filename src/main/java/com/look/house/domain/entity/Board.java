package com.look.house.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long bdId;
    @Column(name = "board_title")
    private String bdTitle;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;  //작성자
    @Column(name = "board_content")
    private String bdContent;
    @Column
    private int viewCount;
    @Column(name = "board_date", insertable = false)
    private Date bdDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Like> likes = new ArrayList<Like>();

}
