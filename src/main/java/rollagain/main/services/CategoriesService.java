package rollagain.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rollagain.main.entities.Categories;
import rollagain.main.repositories.CategoriesRepository;


@Service
public class CategoriesService
{
    @Autowired
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(final CategoriesRepository categoriesRepository)
    {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Categories> getCategories()
    {
        return categoriesRepository.findAll();
    }
}
