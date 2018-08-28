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

    public List<ProductEntity> getAllProductsInShopCart(String id) {
        List<ProductEntity> lists = new ArrayList<>();
        List<ProductEntity> all = productRepository.findAll();
        for (ProductEntity productEntity : all) {
            if (productEntity.getShopCartEntityList().contains(shopCartRepository.findOne(id))) {
                lists.add(productEntity);
            }
        }
        return lists;
    }


    public void buyProduct(Long productId, String email) {
        if (shopCartRepository.findOne(email) == null) {
            List<ShopCartEntity> shopCartList = new ArrayList<>();
            List<ProductEntity> list = new ArrayList<>();
            ProductEntity productEntity = productRepository.findOneById(productId);
            Double price = productEntity.getPrice();
            list.add(productEntity);
            ShopCartEntity shopCartEntity = new ShopCartEntity(email, 1, 5, price, list);
            shopCartList.add(shopCartEntity);
            productEntity.setShopCartEntityList(shopCartList);
            shopCartRepository.save(shopCartEntity);
        } else {
            ProductEntity productEntity = productRepository.findOneById(productId);
            ShopCartEntity shopCartEntity = shopCartRepository.findOne(email);
            shopCartEntity.getProductList().add(productEntity);
            int itemCount = shopCartEntity.getItemCount();
            shopCartEntity.setItemCount(itemCount + 1);
            Double totalPrice = shopCartEntity.getTotalPrice();
            shopCartEntity.setTotalPrice(totalPrice + productEntity.getPrice());
            List<ShopCartEntity> shopCartEntityList = productEntity.getShopCartEntityList();
            shopCartEntityList.add(shopCartEntity);
            productEntity.setShopCartEntityList(shopCartEntityList);
            shopCartRepository.save(shopCartEntity);
        }
    }
}
