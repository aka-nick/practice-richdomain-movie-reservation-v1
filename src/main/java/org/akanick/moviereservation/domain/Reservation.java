package org.akanick.moviereservation.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer feeAmount;

    @Column(name="fee_currency")
    private Integer feeCurrency;

    @Column(name = "audience_count")
    private Integer audienceCount;
}
