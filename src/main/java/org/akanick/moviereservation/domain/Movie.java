package org.akanick.moviereservation.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
