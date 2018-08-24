package solshop.product.model;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    public Set<ProductDTO> toProductDTO(Collection<ProductEntity> productEntities) {
        if ( productEntities == null ) {
            return null;
        }

        Set<ProductDTO> set = new HashSet<>( Math.max( (int) ( productEntities.size() / .75f ) + 1, 16 ) );
        for ( ProductEntity productEntity : productEntities ) {
            set.add( toProductDTO( productEntity ) );
        }
        return set;
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
}
