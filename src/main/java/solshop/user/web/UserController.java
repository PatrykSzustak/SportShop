package solshop.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import solshop.user.model.UserDTO;
import solshop.user.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/index")
    String index() {
        return "index";
    }

    @RequestMapping("/registration")
    String registration(Model model) {
        model.addAttribute(new UserDTO());
        return "registration";
    }

    @RequestMapping("/sklepUser")
    String sklepUser() {
        return "sklepUser";
    }

    @RequestMapping("/sklep")
    String sklep() {
        return "sklep";
    }


    @PostMapping("/add")
    private String createUser(@Valid UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        userService.saveUser(user);
        return "redirect:/index";
    }


}
