package solshop.product.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import solshop.shopCart.model.ShopCartEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    private Double price;
    /*@ManyToMany
    private List<ShopCartEntity> shopCartEntityList;*/

    public ProductEntity() {
    }

    public ProductEntity(String name, Double price) {
        this.name = name;
        this.price = price;

    }
/*
    public ProductEntity(String name, Double price,List<ShopCartEntity> list) {
        this.name = name;
        this.price = price;
        this.shopCartEntityList = list;
    }*/
}
