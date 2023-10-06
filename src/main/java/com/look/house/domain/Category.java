package com.look.house.domain;


import lombok.Data;

@Data
public class Category {

    private  Long cateId;
    private  String name;
    private Long cateParentId;


}
