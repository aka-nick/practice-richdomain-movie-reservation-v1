package org.akanick.moviereservation.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Table(name = "showing")
@Getter
public class Showing {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name="sequence")
    private Integer sequence;

    @Column(name="showing_time")
    private LocalDateTime showingTime;

}
