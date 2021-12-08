package rollagain.main.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import rollagain.main.entities.Categories;
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


    public void addNewProduct(final Products product)
    {
        if (StringUtils.isEmpty(product.getCategory())) {
            throw new IllegalStateException("Category cannot be null");
        }

        Categories category = categoriesRepository.findByCategory(product.getCategory().getCategory().toLowerCase());
        Users user = userRepository.findUsersByEmail(product.getUsers().getEmail());

        if (category != null && user != null) {
            productsRepository.save(createNewProduct(product, category, user));
        }

    }
    private Products createNewProduct(Products product, Categories category, Users user) {
        Products newProduct = new Products();
        newProduct.setCategory(category);
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setState(EnumStateProducts.ON_SALE.name());
        newProduct.setPicture(product.getPicture());
        newProduct.setUsers(user);
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

/*        if (newProduct.getCategory().getCategory() != null && newProduct.getCategory().getCategory().length() > 0 && !Objects.equals(product.getCategory().getCategory().toLowerCase(),
            newProduct.getCategory().getCategory().toLowerCase())) {
            product.setCategory(newProduct.getCategory());
        }*/
        productsRepository.flush();
    }

}
