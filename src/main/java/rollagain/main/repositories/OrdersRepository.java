package rollagain.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Orders;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>
{
    List<Orders> findOrdersById(Long userId);
}
