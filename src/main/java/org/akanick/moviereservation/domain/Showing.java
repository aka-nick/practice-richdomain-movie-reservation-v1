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

    public boolean isDayOfWeek(Integer dayOfWeek) {
        // 월요일 1 ~ 일요일 7
        return showingTime.getDayOfWeek().getValue() == dayOfWeek;
    }

    public boolean isDurationBetween(LocalDateTime startTime, LocalDateTime endTime) {
        // showing 시작 시간이 startTime~endTime에 속하면 true
        // plusNanos(1) : 이벤트 종료 시간에 상영이 딱 맞춰 끝날때 false 되는 것 방지
        return startTime.isEqual(showingTime) ||
                startTime.isBefore(showingTime) && showingTime.plusNanos(1).isAfter(endTime);
    }
}
