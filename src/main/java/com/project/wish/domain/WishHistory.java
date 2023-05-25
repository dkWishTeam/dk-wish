package com.project.wish.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class WishHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "wish_id")
    private Long wishId;
    private LocalDateTime historyDatetime;
    private Long amount;
    private LocalDateTime registerDatetime;
    private LocalDateTime modifyDatetime;

    @Builder
    public WishHistory(Long wishId, LocalDateTime historyDatetime, Long amount, LocalDateTime registerDatetime,
        LocalDateTime modifyDatetime) {
        this.wishId = wishId;
        this.historyDatetime = historyDatetime;
        this.amount = amount;
        this.registerDatetime = registerDatetime;
        this.modifyDatetime = modifyDatetime;
    }

    @Builder
    public WishHistory(Long id, Long wishId, LocalDateTime historyDatetime, Long amount, LocalDateTime registerDatetime,
        LocalDateTime modifyDatetime) {
        this.id = id;
        this.wishId = wishId;
        this.historyDatetime = historyDatetime;
        this.amount = amount;
        this.registerDatetime = registerDatetime;
        this.modifyDatetime = modifyDatetime;
    }

}
