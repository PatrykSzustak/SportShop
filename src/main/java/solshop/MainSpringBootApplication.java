package solshop;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import solshop.currency.Currency;
import solshop.currency.Rates;
import solshop.currency.service.CurrencyService;
import solshop.product.model.ProductDTO;
import solshop.product.service.ProductService;
import solshop.user.model.UserDTO;
import solshop.user.service.UserService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MainSpringBootApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(MainSpringBootApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainSpringBootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBootApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    CommandLineRunner commandLineRunner(RestTemplate restTemplate, ProductService ps, UserService us, CurrencyService cs) {
        return args -> {

            final String url = "http://api.nbp.pl/api/exchangerates/rates/c/usd/today";
            Currency currency = restTemplate.getForObject(url, Currency.class);
            cs.saveCurrencyInService(currency);
            log.info(currency.toString());


            us.saveAdmin(new UserDTO("admin@gmail.com", "admin", "admin"));
            us.saveUser(new UserDTO("user@gmail.com", "user", "user"));
            us.saveUser(new UserDTO("patryk@patryk.pl", "patryk", "patryk"));
            ps.saveProduct(new ProductDTO("Buty", 100.00));
            ps.saveProduct(new ProductDTO("Spodenki", 80.00));
            ps.saveProduct(new ProductDTO("Piłka", 20.00));
            ps.saveProduct(new ProductDTO("Narty", 450.00));
            ps.saveProduct(new ProductDTO("Snowboard", 460.00));
            ps.saveProduct(new ProductDTO("Getry", 10.00));
            ps.saveProduct(new ProductDTO("Plecak", 20.00));
        };
    }


}
