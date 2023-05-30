package com.project.wish.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDtoByAdmin {

    private Long id;
    private String nickname;
    private boolean isBlock;
}
