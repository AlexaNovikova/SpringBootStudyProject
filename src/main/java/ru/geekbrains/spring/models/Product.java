package ru.geekbrains.spring.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name = "price")
    private  int price;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setPrice(int price) {
       if(price>0) {
           this.price = price;
       }
       else throw new IllegalArgumentException("Цена не может быть отрицательным числом.");
    }

    public Category getCategory() {
        return category;
    }

    public void incrementPrice(int amount){
        price+=amount;
    }

}
