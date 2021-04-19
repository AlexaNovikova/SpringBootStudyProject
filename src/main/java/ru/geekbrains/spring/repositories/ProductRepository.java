package ru.geekbrains.spring.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.controllers.ProductController;
import ru.geekbrains.spring.dao.ProductDao;
import ru.geekbrains.spring.models.Product;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    private List<Product> products;
    private ProductDao productDao;


   @Autowired
   public ProductRepository(ProductDao productDao){
       this.productDao=productDao;
   }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public void save(Product product) {
        productDao.saveOrUpdate(product);
    }

    public Optional<Product> findOneById(Long id) {
      return productDao.findById(id);
    }

    public void deleteById(Long id) {
        Optional<Product> product = productDao.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            productDao.delete(p);
        }
    }

    public void incPrice(Long id) {
        Optional<Product> product = findOneById(id);
       if (product.isPresent()){
           Product p = product.get();
           p.incPrice();
           productDao.saveOrUpdate(p);
       }
    }

    public void decPrice(Long id) {
        Optional<Product> product = findOneById(id);
        if (product.isPresent()){
            Product p = product.get();
            p.decPrice();
            productDao.saveOrUpdate(p);
        }
    }
}
