package rollagain.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.controllers.post.NewProductRequest;
import rollagain.main.entities.Products;
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

    @GetMapping(path = "{productId}")
    public Products getProductById(@PathVariable("productId") Long productId){
        return productsService.getProductById(productId);
    }

    @PostMapping
    public void registerNewProduct(@RequestBody NewProductRequest product) {
        productsService.addNewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteRateById(@PathVariable("productId") Long productId) {
        productsService.deleteProduct(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateUsers(
        @PathVariable("productId") Long productId,
        @RequestBody Products product) {
        productsService.updateProduct(productId, product);
    }
}
