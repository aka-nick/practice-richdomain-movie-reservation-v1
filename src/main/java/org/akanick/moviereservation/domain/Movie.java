package org.akanick.moviereservation.domain;


import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "movie")
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="running_time")
    private Integer runningTime;

    @Column(name="fee_amount")
    private Integer feeAmount;

    @Column(name="fee_currency")
    private Integer feeCurrency;

}
