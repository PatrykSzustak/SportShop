package solshop.currency.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solshop.currency.service.CurrencyService;

@Controller
public class CurrencyController {


    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/search")
    private String changeCurrency(@RequestParam String option, Model model) {
        model.addAttribute("products", currencyService.changeCurrency(option));
        return "/skleptest2";
    }

}
