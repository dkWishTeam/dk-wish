package com.project.wish.domain;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_Id")
    private Long userId;
    private String title;
    private String content;
    private String image;
    private String productName;
    private Long goalAmount;
    private Date goalDate;
    private boolean isPublic;
    private boolean completionStatus;
    private LocalDateTime registerDatetime;
    private LocalDateTime modifyDatetime;


    @Builder
    public Wish(Long userId, String title, String content, String image, String productName, Long goalAmount, Date goalDate, boolean isPublic, boolean completionStatus, LocalDateTime registerDatetime, LocalDateTime modifyDatetime) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.image = image;
        this.productName = productName;
        this.goalAmount = goalAmount;
        this.goalDate = goalDate;
        this.isPublic = isPublic;
        this.completionStatus = completionStatus;
        this.registerDatetime = registerDatetime;
        this.modifyDatetime = modifyDatetime;
    }
}

