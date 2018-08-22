package solshop.product.service;

import org.springframework.stereotype.Service;
import solshop.product.model.ProductDTO;
import solshop.product.model.ProductEntity;
import solshop.product.model.ProductMapper;
import solshop.product.repository.ProductRepository;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public ProductDTO findOne(Long productId) {
        return productMapper.toProductDTO(productRepository.findOneById(productId));
    }

    public Set<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDTO)
                .collect(toSet());
    }

    public void saveProduct(ProductDTO product) {
            ProductEntity productEntity = new ProductEntity(product.getName(),product.getPrice());
            productRepository.save(productEntity);
    }

    public void remove(Long id){
        ProductEntity oneById = productRepository.findOneById(id);
        productRepository.delete(oneById);
    }

}
