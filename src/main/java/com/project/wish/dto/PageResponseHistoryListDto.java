package com.project.wish.dto;

import com.project.wish.dto.common.PageResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseHistoryListDto extends PageResponseDto {

    private List<WishHistoryResponseDto> wishHistoryList;
}
