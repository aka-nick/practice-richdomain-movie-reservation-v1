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
    }

    // 도메인 모델 패턴의 구현 ==========================================================
    public Reservation(Customer customer, Showing showing, Integer audienceCount) {
        this.customerId = customer.getId();
        this.showingId = showing.getId();
        this.feeAmount = showing.calculateFee().times(audienceCount);
        this.audienceCount = audienceCount;
    }
}
