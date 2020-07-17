package project.assistant.MvcController;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Controller
@RequestMapping(value ={"","/","Home"})
public class HomeController {


    @RequestMapping(value = {"","/","/index"}, method = RequestMethod.GET)
    public String home(){
        return "homeView/home";
    }



}
