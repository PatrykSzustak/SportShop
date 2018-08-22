package solshop.product.model;

import java.util.Collection;
import java.util.Set;

public interface ProductMapper {


    ProductDTO toProductDTO(ProductEntity productEntity);

    Set<ProductDTO> toProductDTO(Collection<ProductEntity> productEntities);

}
