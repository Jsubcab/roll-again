package rollagain.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Products;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>
{
}
