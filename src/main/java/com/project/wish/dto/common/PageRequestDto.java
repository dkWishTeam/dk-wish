package com.project.wish.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequestDto {

    @Builder.Default
    private Integer page = 0;           //null 대비
    @Builder.Default
    private Integer pageSize = 10;      //한 페이지당 보여줄 게시글 갯수

    public Integer getPage() {
        page = page -1;                 //클라이언트 쪽에서 요청하는 페이지 쪽수는 1부터 시작, jpa에 페이지 요청을 날릴 때는 0부터 시작
        if (page < 0) {
            page = 0;
        }
        return page;
    }
}
