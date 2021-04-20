package ru.geekbrains.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.models.Product;
import ru.geekbrains.spring.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findOneById(Long id) {
        return productRepository.findOneById(id);
    }

    public void save(Product student) {
        productRepository.save(student);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void incPrice(Long id) {
        productRepository.incPrice(id);
    }

    public void decPrice(Long id) {
        productRepository.decPrice(id);
    }
}
