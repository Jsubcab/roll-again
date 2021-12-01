package rollagain.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long>
{
}
