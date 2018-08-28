package solshop.shopCart.model;

public interface ShopCartMapper {

    ShopCartDTO toShopCartDTO(ShopCartEntity shopCartEntity);


    ShopCartEntity toShopCartEntity(ShopCartDTO shopCartDTO);
}
