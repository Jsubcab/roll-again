package rollagain.main.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import rollagain.main.controllers.data.ProductsResponse;
import rollagain.main.controllers.post.NewProductRequest;
import rollagain.main.entities.Categories;
import rollagain.main.entities.Permissions;
import rollagain.main.entities.Products;
import rollagain.main.entities.Users;
import rollagain.main.repositories.CategoriesRepository;
import rollagain.main.repositories.ProductsRepository;
import rollagain.main.repositories.UserRepository;
import rollagain.main.services.enums.EnumStateProducts;


@Service
public class ProductsService
{
    @Autowired
    private final ProductsRepository productsRepository;

    @Autowired
    private final CategoriesRepository categoriesRepository;

    @Autowired
    private final UserRepository userRepository;

    public ProductsService(final ProductsRepository productsRepository,
                           final CategoriesRepository categoriesRepository,
                           final UserRepository userRepository)
    {
        this.productsRepository = productsRepository;
        this.categoriesRepository = categoriesRepository;
        this.userRepository = userRepository;
    }

    public List<Products> getProducts(){
        return productsRepository.findAll();
    }

    public Products getProductById(Long productId){
        Products product = productsRepository.findById(productId)
            .orElseThrow(() -> new IllegalStateException(
                "Product with id " + productId + " does not exist."));
        return product;
    }


    public void addNewProduct(final NewProductRequest newProductRequest)
    {
        Optional<Users> user = userRepository.findById(newProductRequest.getUserId());
        if (!user.isPresent()) {
            throw new IllegalStateException("Invalid user");
        }

        Categories category = categoriesRepository.findByCategory(newProductRequest.getCategory());
        if(category == null) {
            throw new IllegalStateException("Invalid category");
        }

        productsRepository.save(createNewProduct(newProductRequest, category, user.get()));
    }

    private Products createNewProduct(NewProductRequest product, Categories category, Users user) {
        Products newProduct = new Products();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setState(EnumStateProducts.ON_SALE.name());
        newProduct.setPicture(product.getPicture());
        newProduct.setCategory(category);
        newProduct.setUsers(user);
        newProduct.setPrice(product.getPrice());
        return newProduct;
    }

    public void deleteProduct(Long productId) {
        boolean exists = productsRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException("Product with id " + productId + " does not exists.");
        }
        productsRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Long productId,
                           Products newProduct) {

        Products product = productsRepository.findById(productId)
            .orElseThrow(() -> new IllegalStateException(
                "Product with id " + productId + " does not exist."));

        if (newProduct.getName() != null && newProduct.getName() .length() > 0 && !Objects.equals(product.getName(),
            newProduct.getName() )) {
            product.setName(newProduct.getName());
        }

        if (newProduct.getDescription() != null && newProduct.getDescription().length() > 0 && !Objects.equals(product.getDescription(),
            newProduct.getDescription())) {
            product.setDescription(newProduct.getDescription());
        }

        if (newProduct.getPicture() != null && newProduct.getPicture().length() > 0 && !Objects.equals(product.getPicture(),
            newProduct.getPicture())) {
            product.setPicture(newProduct.getPicture());
        }

        if (newProduct.getPrice() != null && !Objects.equals(product.getPrice(),
            newProduct.getPrice())) {
            product.setPrice(newProduct.getPrice());
        }

        if (product.getCategory() != null &&
            product.getCategory() != null
            && product.getCategory().getCategory() != null
            && newProduct.getCategory() != null
            && !product.getCategory().getCategory().equals(newProduct.getCategory().getCategory())) {

            Categories newCategory = categoriesRepository.findByCategory(newProduct.getCategory().getCategory());
            product.setCategory(newCategory);
        }
        productsRepository.save(product);
        productsRepository.flush();
    }

}
