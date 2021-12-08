package rollagain.main.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Permissions;
import rollagain.main.entities.Users;


@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long>
{
    Permissions findPermissionsByLevel(String level);
    Permissions findByLevel(String level);
}
