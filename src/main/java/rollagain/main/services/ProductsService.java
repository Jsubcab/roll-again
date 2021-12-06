package rollagain.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rollagain.main.entities.Products;
import rollagain.main.entities.Users;
import rollagain.main.repositories.ProductsRepository;


@Service
public class ProductsService
{
    @Autowired
    private final ProductsRepository productsRepository;

    public ProductsService(final ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
    }

    public List<Products> getProducts(){
        return productsRepository.findAll();
    }

    public void addNewProduct(final Products product)
    {
        productsRepository.save(product);
    }
}
