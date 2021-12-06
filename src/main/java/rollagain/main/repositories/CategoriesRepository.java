package rollagain.main.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rollagain.main.entities.Categories;


@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long>
{
    Categories findByCategory(String category);
}
