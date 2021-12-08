package rollagain.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Orders;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>
{
}
