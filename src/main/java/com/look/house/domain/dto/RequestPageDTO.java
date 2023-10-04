package com.look.house.domain.dto;

import com.look.house.domain.paging.Pagination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPageDTO {
    //검색이랑 같이 돌릴 때만 사용
    private SearchDTO searchDTO;
    private Pagination pagination;
}
