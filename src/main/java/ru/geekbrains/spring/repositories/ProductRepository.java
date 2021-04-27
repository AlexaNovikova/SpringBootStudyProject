package ru.geekbrains.spring.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.models.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findOneByName(String name);
    List<Product> findAllByPriceLessThan (int price);
    Page<Product> findAllByPriceBetween (Pageable pageable, int min, int max);
    List<Product> findAllByIdLessThanAndPriceGreaterThan (Long id, int minPrice);
    Page<Product> findAllByPriceBetweenAndNameLike(Pageable pageable, int min, int max, String partOfName);

    @Query("select p from Product p where p.id = :id")
    List<Product> hqlFindById(Long id);

}
