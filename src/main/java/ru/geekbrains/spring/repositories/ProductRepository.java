package ru.geekbrains.spring.repositories;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.models.Category;
import ru.geekbrains.spring.models.Product;
import ru.geekbrains.spring.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    private List<Product> products;
    private HibernateUtils hibernateUtils;


   @Autowired
   public ProductRepository(HibernateUtils hibernateUtils){
       this.hibernateUtils= hibernateUtils;
   }

    public List<Product> findAll() {
    try(Session session = hibernateUtils.getCurrentSession()){
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product").getResultList();
        for (Product p: products) {
            p.getCategory();
        }
        session.getTransaction().commit();
        return products;
    }
    }

    public void save(Product product, String category_name) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            List<Category> categories = session.createQuery("from Category").getResultList();
            for (Category c: categories) {
                if (c.getName().equals(category_name)){
                   product.setCategory(c);
                }
            }
           if(product.getCategory()==null){
                Category newCategory = new Category( category_name);
                session.saveOrUpdate(newCategory);
                product.setCategory(newCategory);
            }
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public Optional<Product> findOneById(Long id) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
           Optional<Product> product = Optional.ofNullable(session.get(Product.class, id));
            session.getTransaction().commit();
            return product;
        }
    }

    public void deleteById(Long id) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = " + id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public List<Product> findProductsById(Long id) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            List<Product> products;
            if (session.get(Category.class, id)!=null) {
                Optional<Category> category = Optional.ofNullable(session
                        .createNamedQuery("withProducts", Category.class)
                        .setParameter("id", id)
                        .getSingleResult());
                products = category.map(Category::getProducts).orElse(null);
                session.getTransaction().commit();
                return products;
            }
           return null;
        }
    }

    public Category findCategory(Long category_id) {
       try(Session session = hibernateUtils.getCurrentSession()){
           session.beginTransaction();
           Optional<Category> category = Optional.ofNullable(session.get(Category.class, category_id));
           if (category.isPresent()){
               return category.get();
           }
           return null;
       }
    }
}
