package solshop.shopCart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solshop.shopCart.service.ShopCartService;

import java.security.Principal;

@Controller
public class ShopCartController {
    @Autowired
    private ShopCartService shopCartService;

    public ShopCartController(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @PostMapping("/buyproduct")
    private String buyProduct(@RequestParam("id") Long id, Principal principal) {

        String name = principal.getName();
        shopCartService.buyProduct(id, name);

        return "redirect:/skleptest2";
    }

    @GetMapping("/shopCart")
    public String displayProductsInShopCart(Model model, Principal principal) {
        String name = principal.getName();

        model.addAttribute("productsList", shopCartService.getProductListFromShopCart(name));
        model.addAttribute("totalPrice", shopCartService.getTotalPriceFromShopCart(name));

        return "shopCart";
    }
    @PostMapping("/removeproductfromshopcart")
    public String removeProductFromShopCart(@RequestParam("id") Long id, Principal principal){
        String name = principal.getName();
        shopCartService.remove(id,name);
        return "redirect:/shopCart";
    }
}
