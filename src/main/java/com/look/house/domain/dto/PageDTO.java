package com.look.house.domain.dto;

import lombok.Data;

@Data
public class PageDTO {
    //페이징 처리 될 데이터 변수
    private int recordCountPerPage;		//한 페이지당 게시되는 게시물 수
    private int totalRecordCount;		//전체 게시물 수
    private int firstRecordIndex; 		//페이징 sql의 조건절에 사용되는 시작 rownum
    //페이징 변수
    private int pageSize;				//페이지 리스트에 게시되는 페이지 수
    private int realEnd;				//페이징 마지막 숫자
    private int currentPageNo;			//현재 페이지 번호
    private int firstPageNoOnPage;	//페이지 리스트의 첫 페이지 번호
    private int lastPageNoOnPage;	//페이지 리스트의 마지막 페이지 번호
    private boolean xprev = false;		//이전버튼
    private boolean xnext = false;		//다음버튼

    public PageDTO(int pagesize, int recordCountperPage, int totalRecordcount, int currentpageNo) {
        this.recordCountPerPage = recordCountperPage;
        this.totalRecordCount = totalRecordcount;
        this.currentPageNo = currentpageNo;
        this.pageSize = pagesize;
        //페이징 첫번째 번호
        this.lastPageNoOnPage = (int)(Math.ceil(currentPageNo/10.0)) * 10;
        this.firstPageNoOnPage = lastPageNoOnPage - 9;
        //페이징 마지막 번호
        this.realEnd = (int)(Math.ceil((totalRecordCount * 1.0) / recordCountPerPage));
        if(realEnd < lastPageNoOnPage) {
            this.lastPageNoOnPage = realEnd;
        }
        //페이지에 표시되는 첫번째 인덱스 번호
        this.firstRecordIndex = (currentPageNo - 1) * recordCountPerPage;
        //이전, 다음 버튼
        this.xprev = firstPageNoOnPage > 1;
        if(firstPageNoOnPage == 1){
            this.xprev = false;
        }
        this.xnext = lastPageNoOnPage < realEnd;
    }

    public static class responsePage {
        //전체 데이터 수
        private int totalRecordCount;
        //전체 페이지 수
        private int realEnd;
        //isLastPage
        private boolean xpr;
        //isFirstPage
        public responsePage() {

        }

    }

//    public class requestPage extends PageDTO{
//        public requestPage(int currentPage){
//            this.setCurrentPageNo(currentPage);
//        }
//        //검색 키워드
//    }
}
