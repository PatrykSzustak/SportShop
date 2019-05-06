package solshop.product.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solshop.product.model.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
   ProductEntity findOneById(Long id);

}
