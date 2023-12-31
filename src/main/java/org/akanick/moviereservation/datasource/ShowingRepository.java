package org.akanick.moviereservation.datasource;

import org.akanick.moviereservation.domain.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {

}
