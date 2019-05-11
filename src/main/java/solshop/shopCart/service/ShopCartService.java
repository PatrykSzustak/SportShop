package solshop.shopCart.service;

import org.springframework.stereotype.Service;
import solshop.product.model.ProductEntity;
import solshop.product.model.ProductMapper;
import solshop.product.repository.ProductRepository;
import solshop.product.service.ProductService;
import solshop.shopCart.model.ShopCartDTO;
import solshop.shopCart.model.ShopCartEntity;
import solshop.shopCart.model.ShopCartMapper;
import solshop.shopCart.repository.ShopCartRepository;
import solshop.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCartService {
    private ShopCartRepository shopCartRepository;
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private UserRepository userRepository;
    private ShopCartMapper shopCartMapper;
    private ProductService productService;

    public ShopCartService(ShopCartRepository shopCartRepository, ProductRepository productRepository, ProductMapper productMapper, ShopCartMapper shopCartMapper, ProductService productService) {
        this.shopCartRepository = shopCartRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.shopCartMapper = shopCartMapper;
        this.productService= productService;
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
            List<ShopCartEntity> shopCartList = new ArrayList<>();
            List<ProductEntity> list = new ArrayList<>();
            ProductEntity productEntity = productRepository.findOneById(productId);
            Double price = productEntity.getPrice();
            list.add(productEntity);
            ShopCartEntity shopCartEntity = new ShopCartEntity(email, 1, 5, price, list);
            shopCartList.add(shopCartEntity);
//            productEntity.setShopCartEntityList(shopCartList);
            shopCartRepository.save(shopCartEntity);
        } else if (shopCartRepository.findOne(email).getItemCount() < shopCartRepository.findOne(email).getCapacity()) {
            ProductEntity productEntity = productRepository.findOneById(productId);
            ShopCartEntity shopCartEntity = shopCartRepository.findOne(email);
            shopCartEntity.getProductList().add(productEntity);
            int itemCount = shopCartEntity.getItemCount();
            shopCartEntity.setItemCount(itemCount + 1);
            Double totalPrice = shopCartEntity.getTotalPrice();
            shopCartEntity.setTotalPrice(totalPrice + productEntity.getPrice());
//            List<ShopCartEntity> shopCartEntityList = productEntity.getShopCartEntityList();
//            shopCartEntityList.add(shopCartEntity);
//            productEntity.setShopCartEntityList(shopCartEntityList);
            shopCartRepository.save(shopCartEntity);
        }
    }
    public ShopCartDTO findOne(String email) {
        return shopCartMapper.toShopCartDTO(shopCartRepository.findOne(email));
    }

    /*public void buyProduct(Long productId,String email){
        if (shopCartRepository.findOne(email) == null) {
            List<ShopCartDTO> listShopCartsDTO = new ArrayList<>();
            List<ProductDTO> listProductsDTO = new ArrayList<>();
            ProductDTO one = productService.findOne(productId);
            Double price = one.getPrice();
            listProductsDTO.add(one);
            ShopCartDTO shopCartDTO = new ShopCartDTO(email,listProductsDTO,1,5,price);
            listShopCartsDTO.add(shopCartDTO);
            one.setShopCartDTOList(listShopCartsDTO);
            productMapper.toProductEntity(one);
            ShopCartEntity shopCartEntity = shopCartMapper.toShopCartEntity(shopCartDTO);
            shopCartRepository.save(shopCartEntity);
        }else if (shopCartRepository.findOne(email).getItemCount() < shopCartRepository.findOne(email).getCapacity()) {
            ProductDTO one = productService.findOne(productId);
            ShopCartDTO shopCartDTO = findOne(email);
            shopCartDTO.getList().add(one);
            int itemCount = shopCartDTO.getItemCount();
            shopCartDTO.setItemCount(itemCount+1);
            Double totalPrice = shopCartDTO.getTotalPrice();
            shopCartDTO.setTotalPrice(totalPrice+one.getPrice());
            List<ShopCartDTO> shopCartDTOList = one.getShopCartDTOList();
            shopCartDTOList.add(shopCartDTO);
            one.setShopCartDTOList(shopCartDTOList);
            ShopCartEntity shopCartEntity = shopCartMapper.toShopCartEntity(shopCartDTO);
            productMapper.toProductEntity(one);
            shopCartRepository.save(shopCartEntity);
        }
    }*/
}
