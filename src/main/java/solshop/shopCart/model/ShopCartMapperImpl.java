package solshop.shopCart.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import solshop.product.model.ProductDTO;
import solshop.product.model.ProductEntity;
import solshop.product.model.ProductMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ShopCartMapperImpl implements ShopCartMapper {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public ShopCartDTO toShopCartDTO(ShopCartEntity shopCartEntity) {
        if (shopCartEntity == null) {
            return null;
        }
        ShopCartDTO shopCartDTO = new ShopCartDTO();
        shopCartDTO.setCapacity(shopCartEntity.getCapacity());
        shopCartDTO.setItemCount(shopCartEntity.getItemCount());
        shopCartDTO.setTotalPrice(shopCartEntity.getTotalPrice());
        shopCartDTO.setId(shopCartEntity.getId());
        List<ProductDTO> productDTOS = productMapper.toProductDTO(shopCartEntity.getProductList());
        shopCartDTO.setList(productDTOS);
        return shopCartDTO;
    }

    @Override
    public List<ShopCartDTO> toShopCartDTO(Collection<ShopCartEntity> shopCartEntities) {
        if(shopCartEntities == null){
            return null;
        }
        List<ShopCartDTO> list = new ArrayList<>();
        for (ShopCartEntity shopCart : shopCartEntities) {
            list.add(toShopCartDTO(shopCart));
        }
        return list;
    }

    @Override
    public ShopCartEntity toShopCartEntity(ShopCartDTO shopCartDTO) {
        if (shopCartDTO == null) {
            return null;
        }
        ShopCartEntity shopCartEntity = new ShopCartEntity();
        shopCartEntity.setId(shopCartDTO.getId());
        shopCartEntity.setCapacity(shopCartDTO.getCapacity());
        shopCartEntity.setTotalPrice(shopCartDTO.getTotalPrice());
        shopCartEntity.setItemCount(shopCartDTO.getItemCount());
        List<ProductEntity> productEntities = productMapper.toProductEntity(shopCartDTO.getList());
        shopCartEntity.setProductList(productEntities);
        return shopCartEntity;
    }

    @Override
    public List<ShopCartEntity> toShopCartEntity(Collection<ShopCartDTO> shopCartDTOS) {
        if(shopCartDTOS == null){
            return null;
        }
        List<ShopCartEntity> list = new ArrayList<>();
        for (ShopCartDTO shopCart : shopCartDTOS) {
            list.add(toShopCartEntity(shopCart));
        }
        return list;
    }
}
