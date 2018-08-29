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
import solshop.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ShopCartService {
    private ShopCartRepository shopCartRepository;
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private UserRepository userRepository;
    private ShopCartMapper shopCartMapper;

    public ShopCartService(ShopCartRepository shopCartRepository, ProductRepository productRepository, ProductMapper productMapper, ShopCartMapper shopCartMapper) {
        this.shopCartRepository = shopCartRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
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
//    public Double getTotalPriceFromShopCart(String name){
//        ShopCartDTO shopCartDTO = shopCartMapper.toShopCartDTO(shopCartRepository.findOne(name));
//        Double totalPrice = shopCartDTO.getTotalPrice();
//        return totalPrice;
//    }

//    public ShopCartEntity findOneShopCartById(String name){
//        return shopCartRepository.findOne(name);
//    }


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
        } else if (shopCartRepository.findOne(email).getItemCount() < shopCartRepository.findOne(email).getCapacity()) {
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
