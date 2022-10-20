package net.codejava;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @GetMapping("index")
    public String homeIndex() {
        return "index";
    }

    @GetMapping
    public String homeNoIndex() {
        return "index";
    }

}
