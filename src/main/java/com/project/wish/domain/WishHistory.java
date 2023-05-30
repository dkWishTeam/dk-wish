package com.project.wish.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name="wish_history")

public class WishHistory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "wish_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wish wish;
    private LocalDateTime historyDatetime;
    private Long amount;

    @Builder
    public WishHistory(Wish wish, LocalDateTime historyDatetime, Long amount) {
        this.wish = wish;
        this.historyDatetime = historyDatetime;
        this.amount = amount;

    }
}
