package com.project.wish.dto.common;

import com.example.simpleboardapi.common.utils.PagingUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class PageResponseDto {

    private PagingUtil pagingUtil;
}
