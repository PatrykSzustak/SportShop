package solshop.product.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solshop.currency.service.CurrencyService;
import solshop.product.model.ProductDTO;
import solshop.product.service.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {
    private ProductService productService;
    private CurrencyService currencyService;
    public ProductController(ProductService productService, CurrencyService currencyService) {
        this.productService = productService;
        this.currencyService = currencyService;
    }

    @GetMapping("/skleptest")
    public String displayForAdmin(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute(new ProductDTO());

        return "skleptest";
    }

    @PostMapping("/productEdit")
    public String findIdToEdit(@RequestParam("id") Long id, Model model) {
        ProductDTO one = productService.findOne(id);
        model.addAttribute(one);

        return "productEdit";
    }

    @GetMapping("/skleptest2")
    public String displayForUser(Model model) {

        model.addAttribute("products", productService.getAllProducts());
        Map<String, String> dropdownMap = new HashMap<>();
        dropdownMap.put("PLN", "pln");
        dropdownMap.put("USD", "usd");
        dropdownMap.put("EUR", "eur");
        model.addAttribute("dropDownItems", dropdownMap);
        return "skleptest2";
    }

    @PostMapping("/addproduct")
    private String createProduct(@Valid ProductDTO product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/skleptest";
        }
        productService.saveProduct(product);
        return "redirect:/skleptest";
    }

    @PostMapping("/editproduct")
    private String editProduct(@Valid ProductDTO productDTO, Model model) {

        productService.saveProduct(productDTO);

        return "redirect:/skleptest";
    }

    @PostMapping("/removeproduct")
    private String removeProduct(@RequestParam("id") Long id) {

        productService.remove(id);
        return "redirect:/skleptest";
    }
}
