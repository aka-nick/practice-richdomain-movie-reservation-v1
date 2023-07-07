package org.akanick.moviereservation.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Table(name = "rule")
@Getter
public class Rule {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

//    @Column(name = "discount_id")
//    private Long discountId;
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "position")
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(name = "rule_type")
    private RuleType ruleType;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "sequence")
    private Integer sequence;

    public boolean isTimeOfDayRule() {
        return ruleType == RuleType.TIME_RULE;
    }

    enum RuleType {
        SEQUENCE_RULE, TIME_RULE
    }

}
