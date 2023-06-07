package com.project.wish.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="wish_history")
@ToString

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
