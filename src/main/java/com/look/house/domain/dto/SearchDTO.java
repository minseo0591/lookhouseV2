package com.look.house.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
    private String keyword;           // 검색 키워드
    private String searchType; // 검색 유형
}