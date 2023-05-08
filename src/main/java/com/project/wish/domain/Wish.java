package com.project.wish.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;


@Getter
@Builder
@ToString
public class Wish {

    private Long id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setGoalAmount(long goalAmount) {
        this.goalAmount = goalAmount;
    }

    public void setGoalDate(Date goalDate) {
        this.goalDate = goalDate;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setModifyDatetime(LocalDateTime modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRegisterDatetime(LocalDateTime registerDatetime) {
        this.registerDatetime = registerDatetime;
    }
}

