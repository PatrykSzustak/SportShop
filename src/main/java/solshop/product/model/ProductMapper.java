package solshop.product.model;

import java.util.Collection;
import java.util.List;

public interface ProductMapper {
    ProductDTO toProductDTO(ProductEntity productEntity);
    List<ProductDTO> toProductDTO(Collection<ProductEntity> productEntities);
    ProductEntity toProductEntity(ProductDTO productDTO);
    List<ProductEntity> toProductEntity(Collection<ProductDTO> productDTOS);
}
