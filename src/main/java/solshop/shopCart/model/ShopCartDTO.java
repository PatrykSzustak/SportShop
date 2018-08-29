package solshop.shopCart.model;

import lombok.Data;
import solshop.product.model.ProductDTO;

import java.util.List;

@Data
public class ShopCartDTO {

    private String id;
    private List<ProductDTO> list;
    private int itemCount;
    private int capacity;
    private Double totalPrice;

    public ShopCartDTO() {
    }

    public ShopCartDTO(String id,List<ProductDTO> list,int itemCount,int capacity,Double totalPrice) {
        this.id=id;
        this.list = list;
        this.itemCount = itemCount;
        this.capacity = capacity;
        this.totalPrice = totalPrice;
    }

}
