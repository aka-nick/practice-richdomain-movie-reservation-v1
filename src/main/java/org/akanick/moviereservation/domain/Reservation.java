package org.akanick.moviereservation.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "reservation")
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "showing_id")
    private Long showingId;

    @Column(name="fee_amount")
    private Money feeAmount;

    @Column(name="fee_currency")
    private Money feeCurrency; // 뭔지 모르겠는 필드. 계속 사용되지 않으면 제거할 예정.

    @Column(name = "audience_count")
    private Integer audienceCount;

    protected Reservation() {
    }

    @Builder
    public Reservation(Long customerId, Long showingId, Money feeAmount, Integer audienceCount) {
        this.customerId = customerId;
        this.showingId = showingId;
        this.feeAmount = feeAmount;
        this.audienceCount = audienceCount;
        this.feeCurrency = Money.ZERO;
    }

}
