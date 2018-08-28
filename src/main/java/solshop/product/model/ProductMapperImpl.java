package solshop.product.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toProductDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(productEntity.getId());
        productDTO.setName( productEntity.getName());
        productDTO.setPrice( productEntity.getPrice());

        return productDTO;
    }

    @Override
    public List<ProductDTO> toProductDTO(Collection<ProductEntity> productEntities) {
        if ( productEntities == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<>( Math.max( (int) ( productEntities.size() / .75f ) + 1, 16 ) );
        for ( ProductEntity productEntity : productEntities ) {
            list.add( toProductDTO( productEntity ) );
        }
        return list;
    }

    @Override
    public ProductEntity toProductEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(productDTO.getId());
        productEntity.setName( productDTO.getName());
        productEntity.setPrice( productDTO.getPrice());

        return productEntity;
    }

    @Override
    public List<ProductEntity> toProductEntity(Collection<ProductDTO> productDTOS) {
        return null;
    }
}
