package org.akanick.moviereservation.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;

@Entity
@Table(name = "showing")
@Getter
public class Showing {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

//    @Column(name = "movie_id")
//    private Long movieId;
    // 도메인 모델 패턴 구현으로 넘어가면서 movieId를 직접참조로 변경. 그럴만 하다고 생각한다('상영'이 있으면 '영화'는 무조건 존재하고, 항상 의존할 수밖에 없으므로)


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


    // 도메인 모델 패턴의 구현====================================

    // 도메인 모델 패턴 구현으로 넘어가면서 movieId를 직접참조로 변경. 그럴만 하다고 생각한다('상영'이 있으면 '영화'는 무조건 존재하고, 항상 의존할 수밖에 없으므로)
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, audienceCount);
    }

    public Money calculateFee() {
        return movie.caculateFee(this);
    }

    public boolean isSequence(Integer sequence) {
        return Objects.equals(this.sequence, sequence);
    }
}
