package solshop.product.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import solshop.shopCart.model.ShopCartEntity;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "shopcart_id")
    private ShopCartEntity shopCartEntity;


    public ProductEntity() {
    }

    public ProductEntity(String name, Double price) {
        this.name = name;
        this.price = price;
    }

}
