package solshop.product.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import solshop.shopCart.model.ShopCartMapper;

import java.util.*;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Override
    public ProductDTO toProductDTO(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());
//        productDTO.setShopCartDTOList(shopCartMapper.toShopCartDTO(productEntity.getShopCartEntityList()));
        return productDTO;
    }

    @Override
    public List<ProductDTO> toProductDTO(Collection<ProductEntity> productEntities) {
        if (productEntities == null) {
            return null;
        }
        List<ProductDTO> list = new ArrayList<>(Math.max((int) (productEntities.size() / .75f) + 1, 16));
        for (ProductEntity productEntity : productEntities) {
            list.add(toProductDTO(productEntity));
        }
        return list;
    }

    @Override
    public ProductEntity toProductEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
//        productEntity.setShopCartEntityList(shopCartMapper.toShopCartEntity(productDTO.getShopCartDTOList()));
        return productEntity;
    }

    @Override
    public List<ProductEntity> toProductEntity(Collection<ProductDTO> productDTOS) {
        if (productDTOS == null) {
            return null;
        }
        List<ProductEntity> list = new ArrayList<>(Math.max((int) (productDTOS.size() / .75f) + 1, 16));
        for (ProductDTO productDTO : productDTOS) {
            list.add(toProductEntity(productDTO));
        }
        return list;
    }
}
