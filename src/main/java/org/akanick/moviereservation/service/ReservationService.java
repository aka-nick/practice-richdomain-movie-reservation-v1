package org.akanick.moviereservation.service;

import java.util.List;
import java.util.Objects;
import org.akanick.moviereservation.datasource.CustomerRepository;
import org.akanick.moviereservation.datasource.DiscountRepository;
import org.akanick.moviereservation.datasource.MovieRepository;
import org.akanick.moviereservation.datasource.ReservationRepository;
import org.akanick.moviereservation.datasource.RuleRepository;
import org.akanick.moviereservation.datasource.ShowingRepository;
import org.akanick.moviereservation.domain.Customer;
import org.akanick.moviereservation.domain.Money;
import org.akanick.moviereservation.domain.Movie;
import org.akanick.moviereservation.domain.Reservation;
import org.akanick.moviereservation.domain.Rule;
import org.akanick.moviereservation.domain.Showing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

    private final MovieRepository movieRepository;
    private final ShowingRepository showingRepository;
    private final RuleRepository ruleRepository;
    private final DiscountRepository discountRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;

    public ReservationService(MovieRepository movieRepository,
            ShowingRepository showingRepository,
            RuleRepository ruleRepository,
            DiscountRepository discountRepository,
            ReservationRepository reservationRepository,
            CustomerRepository customerRepository) {
        this.movieRepository = movieRepository;
        this.showingRepository = showingRepository;
        this.ruleRepository = ruleRepository;
        this.discountRepository = discountRepository;
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Reservation reserveShowing(Long customerId, Long showingId, int audienceCount) {
        /*
            (1) 데이터베이스로부터 Movie, Showing, Rule 조회
            (2) Showing에 적용할 수 있는 Rule이 존재하는지 판단
            (3)
            if (Rule이 존재하면) {
                Discount를 읽어 할인된 요금 계산
            } else {
                Movie의 정가를 이용해 요금 계산
            }
            (4) Reservation 생성 후 데이터베이스 저장
         */

        // (1)
        Showing showing = showingRepository.findById(showingId)
                .orElseThrow(() -> new RuntimeException("Showing not found"));
        Movie movie = movieRepository.findById(showing.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        List<Rule> rules = ruleRepository.findAllByMovieId(movie.getId());

        // (2)
        Rule rule = findRule(showing, rules);

        // (3)
        Money fee = movie.getFeeAmount();
        if (rule != null) {
            fee = calculateFee(movie);
        }

        // (4)
        Reservation result = makeReservation(customerId, showingId, audienceCount, fee);
        reservationRepository.save(result);

        return result;
    }

    private Reservation makeReservation(Long customerId,
            Long showingId,
            int audienceCount,
            Money payment) {

        return Reservation.builder()
                .customerId(customerId)
                .showingId(showingId)
                .audienceCount(audienceCount)
                .feeAmount(payment) // audienceCount로 곱해줘야 하지 않나 싶은데... 누락된듯. 기능이 아니라 설계를 보려는거니깐, 일단은 그대로 가자.
                .build();
    }

    private Money calculateFee(Movie movie) {
        final Money[] discountFee = {Money.ZERO};
        discountRepository.findByMovieId(movie.getId())
                .ifPresent(discount -> {
                    if (discount.isAmountType()) {
                        discountFee[0] = Money.wons(discount.getFeeAmount().longValue());
                    } else {
                        discountFee[0] = movie.getFeeAmount().times(discount.getPercent());
                    }
                });
        return movie.getFeeAmount().minus(discountFee[0]);
    }

    private Rule findRule(Showing showing, List<Rule> rules) {

        for (Rule rule : rules) {
            if (rule.isTimeOfDayRule()) { // RuleType.TIME_RULE
                if (showing.isDayOfWeek(rule.getDayOfWeek()) &&
                        showing.isDurationBetween(rule.getStartTime(), rule.getEndTime())) {
                    return rule;
                }
                else { // RuleType.SEQUENCE_RULE
                    if (Objects.equals(rule.getSequence(), showing.getSequence())) {
                        return rule;
                    }
                }
            }
        }

        return null;
    }


    // 도메인 모델 패턴을 적용한 코드 ===========================

    @Transactional
    public Reservation reserveShowingOnDomainModelArchitecture(Long customerId, Long showingId, int audienceCount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Showing showing = showingRepository.findById(showingId)
                .orElseThrow(() -> new RuntimeException("Showing not found"));

        Reservation reservation = showing.reserve(customer, audienceCount);

        reservationRepository.save(reservation);

        return reservation;
    }

}
