package rollagain.main.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.controllers.data.ProductsResponse;
import rollagain.main.controllers.data.UsersResponse;
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
    public List<ProductsResponse> getProducts(){

        final List<Products> productsList = productsService.getProducts();
        if (CollectionUtils.isEmpty(productsList)) {
            return Collections.emptyList();
        }

        List<ProductsResponse> response = new ArrayList<>();
        for (Products p : productsList) {
            ProductsResponse newProduct = createProductsResponse(p);
            response.add(newProduct);
        }
        return response;
    }

    private ProductsResponse createProductsResponse(final Products p)
    {
        ProductsResponse newProduct = new ProductsResponse();
        newProduct.setId(p.getId());
        newProduct.setDescription(p.getDescription());
        newProduct.setName(p.getName());
        newProduct.setPicture(p.getPicture());
        newProduct.setState(p.getState());
        newProduct.setPrice(p.getPrice());

        newProduct.setUser(new UsersResponse());

        newProduct.getUser().setId(p.getUsers().getId());
        newProduct.getUser().setUsername(p.getUsers().getUsername());
        newProduct.getUser().setEmail(p.getUsers().getEmail());
        newProduct.getUser().setCity(p.getUsers().getCity());
        newProduct.getUser().setPhone(p.getUsers().getPhone());
        newProduct.getUser().setZipcode(p.getUsers().getZipcode());
        return newProduct;
    }

    @GetMapping(path = "{productId}")
    public ProductsResponse getProductById(@PathVariable("productId") Long productId){

        final Products productsList = productsService.getProductById(productId);
        ProductsResponse response = new ProductsResponse();
        response.setId(productsList.getId());
        response.setPicture(productsList.getPicture());
        response.setPrice(productsList.getPrice());
        response.setState(productsList.getState());
        response.setDescription(productsList.getDescription());
        response.setName(productsList.getName());

        return response;

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
