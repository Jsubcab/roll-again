package rollagain.main.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.entities.Categories;
import rollagain.main.services.CategoriesService;


@RestController
@RequestMapping(path = "api/categories")
public class CategoriesController
{
    private final CategoriesService categoriesService;

    public CategoriesController(final CategoriesService categoriesService)
    {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<Categories> getCategories() {
        return categoriesService.getCategories();
    }
}
