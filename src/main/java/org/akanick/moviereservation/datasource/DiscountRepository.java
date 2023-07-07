package org.akanick.moviereservation.datasource;

import java.util.Optional;
import org.akanick.moviereservation.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByMovieId(Long id);
}
