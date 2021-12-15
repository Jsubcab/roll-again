package rollagain.main.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.controllers.data.CategoriesResponse;
import rollagain.main.controllers.data.ProductsResponse;
import rollagain.main.controllers.data.UsersResponse;
import rollagain.main.entities.Categories;
import rollagain.main.entities.Products;
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
    public List<CategoriesResponse> getCategories() {
        final List<Categories> categoriesList = categoriesService.getCategories();
        if (CollectionUtils.isEmpty(categoriesList)) {
            return Collections.emptyList();
        }

        List<CategoriesResponse> response = new ArrayList<>();
        for (Categories c : categoriesList) {
            CategoriesResponse newCategory = createCategoriesResponse(c);
            response.add(newCategory);
        }
        return response;

        //return categoriesService.getCategories();
    }

    private CategoriesResponse createCategoriesResponse(final Categories c)
    {
        CategoriesResponse newCategory = new CategoriesResponse();
        newCategory.setId(c.getId());
        newCategory.setCategory(c.getCategory());

        // TO ADD PRODUCTS
        // newUser.getProducts().setId(u.getProducts().get);

        return newCategory;
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
