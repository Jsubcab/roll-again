package rollagain.main.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rollagain.main.entities.Categories;
import rollagain.main.entities.Products;
import rollagain.main.entities.Users;
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

    public Categories getCategoriesById(Long categoriesId)
    {
        Categories category = categoriesRepository.findById(categoriesId)
            .orElseThrow(() -> new IllegalStateException(
                "Category with id " + categoriesId + " does not exist."));
        return category;
    }

    public void addNewCategory(Categories category)
    {
        categoriesRepository.save(category);
    }

    public void deleteRate(final Long categoryId)
    {
        boolean exists = categoriesRepository.existsById(categoryId);
        if (!exists) {
            throw new IllegalStateException("Product with id " + categoryId + " does not exists.");
        }
        categoriesRepository.deleteById(categoryId);
    }

    public void updateCategory(final Long categoryId, final Categories newCategory)
    {
        Categories category = categoriesRepository.findById(categoryId)
            .orElseThrow(() -> new IllegalStateException(
                "Category with id " + categoryId + " does not exist."));

        if (newCategory.getCategory() != null && newCategory.getCategory().length() > 0 && !Objects.equals(category.getCategory(),
            newCategory.getCategory())) {
            category.setCategory(newCategory.getCategory());
            categoriesRepository.flush();
        }

    }
}
