package net.codejava;

import net.codejava.Database.Connection;
import net.codejava.Database.User;
import net.codejava.Database.UserDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login_form";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute("user") User user) {
        System.out.println(user);
       try {
           if (UserDatabase.validateUser(user.getName(), user.getPassword())) {
               return "login_unsuccess";
           } else {
               if (UserDatabase.isInjected()) {
                   return "login_injected";
               } else {
                   return "login_unsuccess";
               }
           }
       } catch (Exception e) {
           e.getMessage();
           return "";
       }
    }

}
