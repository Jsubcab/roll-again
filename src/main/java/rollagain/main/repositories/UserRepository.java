package rollagain.main.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Permissions;
import rollagain.main.entities.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long>
{
    //@Query("SELECT u FROM Users u WHERE u.email = ?1")
    Users findUsersByEmail(String email);
    Optional<Users> findUsersByUsername(String username);

    Boolean existsUsersByEmail(String email);

    Boolean existsUsersByUsername(String username);

    @Query(value = "SELECT * FROM Users u WHERE u.permission_id = ?1", nativeQuery = true)
    Permissions findPermissionById(Long permission_id);
}
