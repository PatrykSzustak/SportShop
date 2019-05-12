package solshop.shopCart.service;

import org.springframework.stereotype.Service;
import solshop.product.model.ProductEntity;
import solshop.product.repository.ProductRepository;
import solshop.shopCart.model.ShopCartDTO;
import solshop.shopCart.model.ShopCartEntity;
import solshop.shopCart.model.ShopCartMapper;
import solshop.shopCart.repository.ShopCartRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCartService {
    private ShopCartRepository shopCartRepository;
    private ProductRepository productRepository;
    private ShopCartMapper shopCartMapper;

    public ShopCartService(ShopCartRepository shopCartRepository, ProductRepository productRepository, ShopCartMapper shopCartMapper) {
        this.shopCartRepository = shopCartRepository;
        this.productRepository = productRepository;
        this.shopCartMapper = shopCartMapper;
    }

    public List<ProductEntity> getProductListFromShopCart(String userEmail) {
        if (shopCartRepository.findOne(userEmail) == null) {
            ShopCartEntity shopCartEntity = new ShopCartEntity(userEmail, 0, 5, 0.0, new ArrayList<ProductEntity>());
            return shopCartEntity.getProductList();
        } else {
            return shopCartRepository.findOne(userEmail).getProductList();
        }
    }

    public Double getTotalPriceFromShopCart(String userEmail) {
        if (shopCartRepository.findOne(userEmail) == null) {
            ShopCartEntity shopCartEntity = new ShopCartEntity(userEmail, 0, 5, 0.0, new ArrayList<ProductEntity>());
            return shopCartEntity.getTotalPrice();
        }
        return shopCartRepository.findOne(userEmail).getTotalPrice();
    }

    public void remove(Long productId,String shopCartId){
        ProductEntity oneById = productRepository.findOneById(productId);
        ShopCartEntity one = shopCartRepository.findOne(shopCartId);
        one.getProductList().remove(oneById);
        shopCartRepository.save(one);
    }

    public void buyProduct(Long productId, String email) {
        if (shopCartRepository.findOne(email) == null) {
            List<ProductEntity> list = new ArrayList<>();
            ProductEntity productEntity = productRepository.findOneById(productId);
            Double price = productEntity.getPrice();
            list.add(productEntity);
            ShopCartEntity shopCartEntity = new ShopCartEntity(email, 1, 5, price, list);
            shopCartRepository.save(shopCartEntity);
        } else if (shopCartRepository.findOne(email).getItemCount() < shopCartRepository.findOne(email).getCapacity()) {
            ProductEntity productEntity = productRepository.findOneById(productId);
            ShopCartEntity shopCartEntity = shopCartRepository.findOne(email);
            shopCartEntity.getProductList().add(productEntity);
            int itemCount = shopCartEntity.getItemCount();
            shopCartEntity.setItemCount(itemCount + 1);
            Double totalPrice = shopCartEntity.getTotalPrice();
            shopCartEntity.setTotalPrice(totalPrice + productEntity.getPrice());
            shopCartRepository.save(shopCartEntity);
        }
    }
    public ShopCartDTO findOne(String email) {
        return shopCartMapper.toShopCartDTO(shopCartRepository.findOne(email));
    }
}
