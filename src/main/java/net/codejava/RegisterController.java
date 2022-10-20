package net.codejava;

import net.codejava.Database.User;
import net.codejava.Database.UserDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

	private String username;
	private String password;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register_form";
	}
	
	@PostMapping("/register")
	public String submitRegisterForm(@ModelAttribute("user") User user) {
		username = user.getName();
		password = user.getPassword();
		UserDatabase.addUser(username, password);
		System.out.println(user);
		return "register_success";
	}
}
