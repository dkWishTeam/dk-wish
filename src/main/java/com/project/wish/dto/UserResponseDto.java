package com.project.wish.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDto {
    private Long id;
    private String userId;
    private String email;
    private String name;
    private Date birth;
    private String phone;
    private String nickname;
    private LocalDateTime registerDateTime;
}
