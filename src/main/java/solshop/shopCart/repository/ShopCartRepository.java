package solshop.shopCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solshop.shopCart.model.ShopCartEntity;

@Repository
public interface ShopCartRepository extends JpaRepository<ShopCartEntity,String> {

    @Override
    ShopCartEntity findOne(String id);

}
