package rollagain.main.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "{categoryId}")
    public Categories getCategoriesById(@PathVariable("categoryId") Long categoryId) {
        return categoriesService.getCategoriesById(categoryId);
    }

    @PostMapping
    public void registerNewCategory(@RequestBody Categories category) {
        categoriesService.addNewCategory(category);
    }

    @DeleteMapping(path = "{categoryId}")
    public void deleteRateById(@PathVariable("categoryId") Long categoryId) {
        categoriesService.deleteRate(categoryId);
    }

    @PutMapping(path = "{categoryId}")
    public void updateUsers(
        @PathVariable("categoryId") Long categoryId,
        @RequestBody Categories category) {
        categoriesService.updateCategory(categoryId, category);
    }

}
