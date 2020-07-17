package project.assistant.MvcController;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.assistant.Model.Beans.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping(value = "Account")
public class AccountController {

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String register() throws Exception {
        return null;
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {
        if(logout!=null){
            return "redirect:/logout";
        }else {
            return "accountView/login";
        }
    }


    @RequestMapping(value = "/AccessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView("accountView/access-denied");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("email", userDetail.getUsername());
        }
        return model;
    }
}

