package org.akanick.moviereservation.datasource;

import java.util.List;
import org.akanick.moviereservation.domain.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findAllByMovieId(Long id);
}
