package ru.geekbrains.spring.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NoArgsConstructor;
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

    public void setPrice(int price) {
       if(price>0) {
           this.price = price;
       }
       else throw new IllegalArgumentException("Цена не может быть отрицательным числом.");
    }

    public void incPrice(){
           price++;
    }

    public void decPrice(){
       if(price>0) {
           price--;
       }
    }
}
