package rollagain.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long>
{
}
