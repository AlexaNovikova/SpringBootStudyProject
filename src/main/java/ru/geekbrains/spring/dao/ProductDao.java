package ru.geekbrains.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.models.Product;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductDao {
    private SessionFactory sessionFactory;


    @PostConstruct
    public void Init(){
        sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
        prepareData();
}

    public void prepareData() {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            session =sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @PreDestroy
    public void shutdown() {
        sessionFactory.close();
    }


    public Optional <Product> findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            if (product!=null) {
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }

    public List<Product> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "from Product";
            Query query = session.createQuery(hql);
            List<Product> listProducts = query.list();
            for (Product product : listProducts) {
                System.out.println(product.getName());
            }
//            List<Product> products = (List<Product>)session.createQuery("from Product", List.class);
            session.getTransaction().commit();
            return listProducts;
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

    public void delete(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }
    }
}
