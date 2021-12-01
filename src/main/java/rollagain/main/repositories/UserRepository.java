package rollagain.main.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Rates;
import rollagain.main.entities.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long>
{
    //@Query("SELECT u FROM Users u WHERE u.email = ?1")
    Optional<Users> findUsersByEmail(String email);
}
