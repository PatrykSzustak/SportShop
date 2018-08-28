package solshop.shopCart.model;

import org.springframework.stereotype.Component;
import solshop.product.model.ProductDTO;
import solshop.product.model.ProductEntity;
import solshop.product.model.ProductMapper;

import java.util.List;

@Component
public class ShopCartMapperImpl implements ShopCartMapper {

    private ProductMapper productMapper;

    @Override
    public ShopCartDTO toShopCartDTO(ShopCartEntity shopCartEntity) {
        if (shopCartEntity == null) {
            return null;
        }
        ShopCartDTO shopCartDTO = new ShopCartDTO();
        shopCartDTO.setCapacity(shopCartEntity.getCapacity());
        shopCartDTO.setItemCount(shopCartEntity.getItemCount());
        shopCartDTO.setId(shopCartEntity.getId());

        List<ProductDTO> productDTOS = productMapper.toProductDTO(shopCartEntity.getProductList());
        shopCartDTO.setList(productDTOS);

        return shopCartDTO;
    }

    @Override
    public ShopCartEntity toShopCartEntity(ShopCartDTO shopCartDTO) {
        if (shopCartDTO == null) {
            return null;
        }
        ShopCartEntity shopCartEntity = new ShopCartEntity();

        shopCartEntity.setId(shopCartDTO.getId());
        shopCartEntity.setCapacity(shopCartDTO.getCapacity());
        shopCartEntity.setItemCount(shopCartDTO.getItemCount());
        List<ProductEntity> productEntities = productMapper.toProductEntity(shopCartDTO.getList());
        shopCartEntity.setProductList(productEntities);

        return shopCartEntity;
    }
}
