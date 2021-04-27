package ru.geekbrains.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page,size));
    }

    public Optional<Product> findOneById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product, String category_name) {
        productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

//
//    public List<Product> findProductsByCategory(Long id) {
//       return productRepository.findProductsById(id);
//    }
//
//    public Category findCategory(Long category_id) {
//        return  productRepository.findCategory(category_id);
//    }


    @Transactional
    public  void  incrementPriceById(Long id, int amount){
        Product p = productRepository.findById(id).get();
        p.incrementPrice(amount);
    }

    public Page <Product> filterByPriceAndName(Pageable pageable, int minPrice, int maxPrice, String partOfName){
        return  productRepository.findAllByPriceBetweenAndNameLike(pageable, minPrice, maxPrice, partOfName);
    }

}
