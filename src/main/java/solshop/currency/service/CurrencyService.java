package solshop.currency.service;

import org.springframework.stereotype.Service;
import solshop.currency.Currency;
import solshop.currency.Rates;
import solshop.product.model.ProductDTO;
import solshop.product.model.ProductEntity;
import solshop.product.repository.ProductRepository;
import solshop.product.service.ProductService;

import java.util.List;
import java.util.Set;

@Service
public class CurrencyService {

    public Currency currency;


    private ProductRepository productRepository;
    private ProductService productService;

    public CurrencyService(ProductRepository productRepository,ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public void saveCurrencyInService(Currency currency){
        this.currency =currency;
    }


    public Set<ProductDTO> changeCurrency(String option) {

        Rates rates = new Rates();
        List<Rates> rates1 = currency.getRates();
        for (Rates rates2 : rates1) {
            Double ask = rates2.getAsk();
            rates.setAsk(ask);
        }
        Set<ProductDTO> allProducts = productService.getAllProducts();
        Double ask = rates.getAsk();
        if (option.equals("usd")) {
            for (ProductDTO allProduct : allProducts) {
                Double price = allProduct.getPrice();
                allProduct.setPrice(price*ask);
            }
            return allProducts;
        }
        return allProducts;
    }


}
