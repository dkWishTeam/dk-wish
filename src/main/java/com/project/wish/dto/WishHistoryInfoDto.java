package com.project.wish.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishHistoryInfoDto {

    private WishUserDto wishUserDto;

    private String title;

    private WishHistoryRateDto wishHistoryRateDto;

    private List<WishHistoryResponseDto> wishHistoryList;

    private String msg = "아직 위시 기록이 없네요. 새로운 기록을 남겨보세요.";

}
