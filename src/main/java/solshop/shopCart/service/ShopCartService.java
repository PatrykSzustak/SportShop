package solshop.shopCart.service;

import org.springframework.stereotype.Service;
import solshop.product.model.ProductDTO;
import solshop.product.model.ProductEntity;
import solshop.product.model.ProductMapper;
import solshop.product.repository.ProductRepository;
import solshop.shopCart.model.ShopCartDTO;
import solshop.shopCart.model.ShopCartEntity;
import solshop.shopCart.model.ShopCartMapper;
import solshop.shopCart.repository.ShopCartRepository;
import solshop.user.model.UserEntity;
import solshop.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopCartService {
    private ShopCartRepository shopCartRepository;
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private ShopCartMapper shopCartMapper;
    private UserRepository userRepository;

    public ShopCartService(ShopCartRepository shopCartRepository, ProductRepository productRepository, ProductMapper productMapper, ShopCartMapper shopCartMapper) {
        this.shopCartRepository = shopCartRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.shopCartMapper = shopCartMapper;
    }


    public void buyProduct(Long productId, String email) {
        if (shopCartRepository.findOne(email) == null) {
            ProductEntity productEntity = productRepository.findOneById(productId);
            Double price = productEntity.getPrice();
            ShopCartEntity shopCartEntity = new ShopCartEntity(email, 1, 5, price);
            shopCartRepository.save(shopCartEntity);
            productEntity.setShopCartEntity(shopCartEntity);
            productRepository.save(productEntity);
        } else {
            if(productRepository.findOneById(productId).getShopCartEntity()==null){
                ProductEntity productEntity = productRepository.findOneById(productId);
                ShopCartEntity shopCartEntity = shopCartRepository.findOne(email);
                int itemCount = shopCartEntity.getItemCount();
                shopCartEntity.setItemCount(itemCount + 1);
                Double totalPrice = shopCartEntity.getTotalPrice();
                shopCartEntity.setTotalPrice(totalPrice + productEntity.getPrice());
                shopCartRepository.save(shopCartEntity);
                productEntity.setShopCartEntity(shopCartEntity);
                productRepository.save(productEntity);
            }

        }
    }
}
