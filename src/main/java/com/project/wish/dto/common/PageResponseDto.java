package com.project.wish.dto.common;


import com.project.wish.utils.PagingUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.project.wish.utils.PagingUtil;

@Getter
@SuperBuilder
@NoArgsConstructor
public class PageResponseDto {

    private PagingUtil pagingUtil;
}
