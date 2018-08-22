package solshop.product.model;

import org.hibernate.validator.constraints.NotBlank;

public class ProductDTO {


    private Long id;
    @NotBlank
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
