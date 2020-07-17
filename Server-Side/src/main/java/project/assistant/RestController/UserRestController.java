package project.assistant.RestController;


import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.assistant.Model.Beans.UserBean;
import project.assistant.Model.ViewBeans.UserViewBean;
import project.assistant.Services.Service.UserService;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final static Logger LOGGER = Logger.getLogger(UserRestController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserViewBean> findAll() {
        List<UserViewBean> userViewBeans=new ArrayList<>();
        for (UserBean userBean : userService.getAll()) {
            userViewBeans.add(convertToViewBean(userBean));
        }
        return userViewBeans;
    }

    @GetMapping("/username")
    public UserViewBean findByUsername(@RequestParam String username) {
        UserBean userBean = userService.getByUsername(username);
        return convertToViewBean(userBean);
    }

    @GetMapping("/login")
    public void login(HttpSession session, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal()!=null && auth.getPrincipal()!="anonymousUser") {

            UserViewBean userViewBean = findByUsername(auth.getName());
            if (auth.getAuthorities().stream().anyMatch(
                    r -> ((GrantedAuthority) r).getAuthority().equals("ROLE_PATIENT"))) {

                session.setAttribute("patient", userViewBean);
                response.sendRedirect("/Patient/index");

            } else {
                if (auth.getAuthorities().stream().anyMatch(
                        r -> ((GrantedAuthority) r).getAuthority().equals("ROLE_PHYSICIAN"))) {

                    session.setAttribute("physician", userViewBean);
                    response.sendRedirect("/Physician/index");
                }
            }
        }else {
            response.sendRedirect("/Account/Login?logout");
        }
    }

    @GetMapping("/{id}")
    public UserViewBean findOne(@PathVariable Long id) {
        return convertToViewBean(userService.getOne(id));
    }


    @PostMapping(value = "register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserViewBean create(@RequestBody UserBean user, @RequestParam String role,
                         HttpSession session, HttpServletResponse response) throws IOException {

        if(role.equals("ROLE_PATIENT")) {
            UserViewBean userViewBean = convertToViewBean(userService.createUser(user, role));
            session.setAttribute("patient", userViewBean);
            return userViewBean;
        }
        else {
            UserViewBean userViewBean = convertToViewBean(userService.createUser(user, role));
            session.setAttribute("physician" , userViewBean);
            return userViewBean;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserViewBean update(@RequestBody UserBean user, @PathVariable Long id) {
        return convertToViewBean(userService.updateUser(user, id));
    }

    private UserViewBean convertToViewBean(UserBean userBean) {
        UserViewBean userViewBean = modelMapper.map(userBean, UserViewBean.class);
        return userViewBean;
    }

//    private UserBean convertToBean(UserViewBean userViewBean) throws ParseException {
//        UserBean userBean = modelMapper.map(userViewBean, UserBean.class);
//        return userBean;
//    }


}
