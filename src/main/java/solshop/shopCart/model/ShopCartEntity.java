package solshop.shopCart.model;

import lombok.Data;
import solshop.product.model.ProductEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopcarts")
@Data
public class ShopCartEntity {
    @Id
    private String id;
    private int itemCount;
    private int capacity;
    private Double totalPrice;

    @ManyToMany(mappedBy = "shopCartEntityList")
    private List<ProductEntity> productList ;

    public ShopCartEntity() {
    }

    public ShopCartEntity(String id,int itemCount, int capacity, Double totalPrice,List<ProductEntity> list) {
        this.id = id;
        this.itemCount = itemCount;
        this.capacity = 5;
        this.totalPrice = totalPrice;
        this.productList = list;
    }
}

