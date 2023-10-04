package com.look.house.domain.paging;


import com.look.house.domain.dto.SearchDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private int page;                 //현재 페이지
    private int recordSize;           // 페이지당 출력할 데이터 개수
    private int pageSize;             // 화면 하단에 출력할 페이지 사이즈
    private int totalRecordCount;     // 전체 데이터 수
    private int totalPageCount;       // 전체 페이지 수
    private int startPage;            // 첫 페이지 번호
    private int endPage;              // 끝 페이지 번호
    private int limitStart;           // LIMIT 시작 위치
    private boolean existPrevPage;    // 이전 페이지 존재 여부
    private boolean existNextPage;    // 다음 페이지 존재 여부

    public Pagination(int recordSizes, int pageSizes) {
        this.recordSize = recordSizes;
        this.pageSize = pageSizes;
    }

    public Pagination changeSizes(int totalRecordCount, int currentpage){
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            calculation(currentpage);
        }
        return new Pagination();
    }

    private void calculation(int currentpage) {
        this.page = currentpage;
        // 전체 페이지 수 계산
        this.totalPageCount = ((totalRecordCount - 1) / recordSize) + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if ( page > totalPageCount) {
            this.page = totalPageCount;
        }

        // 첫 페이지 번호 계산
        this.startPage = ((page - 1) / pageSize) * pageSize + 1;

        // 끝 페이지 번호 계산
        this.endPage = startPage + pageSize - 1;

        // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // LIMIT 시작 위치 계산
        this.limitStart = (page - 1) * this.recordSize;

        // 이전 페이지 존재 여부 확인
        this.existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부 확인
        this.existNextPage = (endPage * recordSize) < totalRecordCount;
    }

}
