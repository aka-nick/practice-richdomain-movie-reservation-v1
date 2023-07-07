package org.akanick.moviereservation.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "discount")
@Getter
public class Discount {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id; // 예시에서는 Movie-Discount가 비식별관계지만, 편의상 식별관계로 변경한다.

    @Column(name = "movie_id")
    private Long movieId;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    private DiscountType discountType;

    @Column(name = "fee_amount")
    private Money feeAmount;

    @Column(name = "fee_currency")
    private Money feeCurrency;

    @Column(name = "percent")
    private Double percent;

    public boolean isAmountType() {
        return discountType == DiscountType.AMOUNT;
    }

    enum DiscountType {
        AMOUNT, PERCENT
    }

}
