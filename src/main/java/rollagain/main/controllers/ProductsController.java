package rollagain.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.entities.Products;
import rollagain.main.entities.Users;
import rollagain.main.services.ProductsService;


@RestController
@RequestMapping(path = "api/products")
public class ProductsController
{
    private final ProductsService productsService;

    @Autowired
    public ProductsController(final ProductsService productsService)
    {
        this.productsService = productsService;
    }

    @GetMapping
    public List<Products> getProducts(){
        return productsService.getProducts();
    }

    @PostMapping
    public void registerNewProduct(@RequestBody Products product) {
        productsService.addNewProduct(product);
    }

}
