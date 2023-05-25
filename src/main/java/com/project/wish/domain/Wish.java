package com.project.wish.domain;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

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

}

