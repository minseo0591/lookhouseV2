package com.look.house.domain.dto;


import com.look.house.domain.Criteria;
import lombok.Data;

@Data
public class PageDTO {

    //페이지 시작 번호 1 ,11,21
    private int startPage;

    //페이지 끝 번호 10,20,30
    private int endPage;

    // 이전 , 다음 버튼 존재 유무
    private boolean next, prev;

    //  데이터전체 갯수
    private int totalCount;


    // 현재 페이지 번호(1) , 한 페이지에 갯수(10) , 검색, 검색 타입
    private Criteria cri;

    public PageDTO(Criteria cri,int total){
        this.cri = cri;
        this.totalCount = total;
        //페이지 끝번호 1,2,3,4,5,6,7,8,9,10 는 pageEnd 가 10
        this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))* 10;

        //페이지 시작번호
        this.startPage = this.endPage -9;

        // ex)  total이 85.0/10 =9
        int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));


        /**
         *   페이지 끝번호 유효성 체크
         *   만약  realEnd =9
         *    pageEnd = 10
         *    이러면 realEnd 가 진짜 값이니깐 9를 pageEnd에 값9를 담는다.
         */
        if(realEnd< endPage){
            this.endPage =realEnd;
        }

        /**
         *  pageStart 1>1 false 이전버튼 생성 x
         */
        this.prev = this.startPage >1;

        /**
         *  ex ) pageEnd 10 < 12 true 다음버튼 생성 O
         */
        this.next =this.endPage <realEnd;

    }


}
