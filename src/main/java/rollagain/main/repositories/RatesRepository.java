package rollagain.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Rates;

@Repository
public interface RatesRepository extends JpaRepository<Rates, Long>
{
    @Query(value = "SELECT * FROM Rates r WHERE r.user_id = ?1", nativeQuery = true)
    List<Rates> findRatesById(Long user_id);
}
