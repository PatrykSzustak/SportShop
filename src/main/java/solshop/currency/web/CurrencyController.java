package solshop.currency.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solshop.currency.Currency;
import solshop.currency.service.CurrencyService;
import solshop.product.service.ProductService;

@Controller
public class CurrencyController {
    private CurrencyService currencyService;
    private ProductService productService;
    public CurrencyController(CurrencyService currencyService,ProductService productService) {
        this.currencyService = currencyService;
        this.productService = productService;
    }
    @GetMapping("/search")
    private String changeCurrency(@RequestParam String option, Model model) {
        Currency currency;
        if (option.equals("usd")) {
            final String url = "http://api.nbp.pl/api/exchangerates/rates/c/usd/today";
            currency = currencyService.getUsdCurrency(url);
            model.addAttribute("products", currencyService.changeCurrency(currency));
        } else if (option.equals("eur")) {
            final String url = "http://api.nbp.pl/api/exchangerates/rates/c/eur/today";
            currency = currencyService.getEurCurrency(url);
            model.addAttribute("products", currencyService.changeCurrency(currency));
        } else if (option.equals("pln")) {
            model.addAttribute("products", productService.getAllProducts());
        }
        return "/skleptest2";
    }
}
