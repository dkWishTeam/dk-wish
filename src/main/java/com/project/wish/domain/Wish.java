package com.project.wish.domain;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wish")
    private List<WishHistory> wishHistories = new ArrayList<>();

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

