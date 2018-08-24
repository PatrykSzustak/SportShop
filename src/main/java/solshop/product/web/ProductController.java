package solshop.product.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solshop.product.model.ProductDTO;
import solshop.product.service.ProductService;

import javax.validation.Valid;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/skleptest")
    public String displayForAdmin(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute(new ProductDTO());

        return "skleptest";
    }

    @PostMapping("/productEdit")
    public String findIdToEdit(@RequestParam("id") Long id,Model model) {
        ProductDTO one = productService.findOne(id);
        model.addAttribute(one);

        return "productEdit";
    }

    @GetMapping("/skleptest2")
    public String displayForUser(Model model) {
        model.addAttribute("products", productService.getAllProducts());
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
