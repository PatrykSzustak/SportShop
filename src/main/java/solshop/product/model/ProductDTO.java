package solshop.product.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ProductDTO {



    private Long id;
    private String name;
    private Double price;

    public ProductDTO(){

    }

    public ProductDTO(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public ProductDTO(Long id,String name, Double price) {
        this.id =id;
        this.name = name;
        this.price = price;
    }


}
