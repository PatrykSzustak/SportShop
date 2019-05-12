package solshop.currency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import solshop.currency.Currency;
import solshop.currency.Rates;
import solshop.product.model.ProductDTO;
import solshop.product.service.ProductService;

import java.util.List;
import java.util.Set;

@Service
public class CurrencyService {
    private ProductService productService;
    private final
    RestTemplate restTemplate;
    @Autowired
    public CurrencyService(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "currency")
    public Currency getUsdCurrency(String key) {
        return restTemplate.getForObject(key, Currency.class);
    }

    @Cacheable(value = "currency")
    public Currency getEurCurrency(String key) {
        return restTemplate.getForObject(key, Currency.class);
    }

    public Set<ProductDTO> changeCurrency(Currency currency) {
        Rates rates = new Rates();
        List<Rates> rates1 = currency.getRates();
        for (Rates rates2 : rates1) {
            Double ask = rates2.getAsk();
            rates.setAsk(ask);
        }
        Set<ProductDTO> allProducts = productService.getAllProducts();
        Double ask = rates.getAsk();
        for (ProductDTO allProduct : allProducts) {
            Double price = allProduct.getPrice();
            allProduct.setPrice(price / ask);
        }
        return allProducts;
    }
}
