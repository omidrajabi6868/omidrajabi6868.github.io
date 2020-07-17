package project.assistant.Services.Service;


import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.assistant.ExceptionHandler.MyIdMismatchException;
import project.assistant.ExceptionHandler.MyNotFoundException;
import project.assistant.Model.Beans.RoleBean;
import project.assistant.Model.Beans.UserBean;
import project.assistant.Repositories.IRoleRepository;
import project.assistant.Repositories.IUserRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    private final static Logger LOGGER= Logger.getLogger(UserService.class);

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;


    public List<UserBean> getAll() {
        List<UserBean> userBeans=new ArrayList<>();
        userRepository.findAll().forEach(userBean -> userBeans.add(userBean));
        return userBeans;
    }

    public UserBean getByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        }catch (Exception ex){
           LOGGER.debug((ex.getMessage()));
           throw new MyNotFoundException("User not found", new Throwable());
        }
    }

    public UserBean getOne(Long id) {
        try {
            return userRepository.findById(id).get();
        }catch (Exception ex){
            LOGGER.debug(ex.getMessage());
            throw new MyNotFoundException("User not found",new Throwable());
        }
    }


    public UserBean createUser(UserBean userBean, String role) {

        Set<RoleBean> roleBeanSet = new HashSet<>();
        RoleBean roleBean = roleRepository.findByRole(role);
        if (!roleBean.equals(null)) {
            roleBeanSet.add(roleBean);
            userBean.setRoleBeans(roleBeanSet);
            return userRepository.save(userBean);
        } else {
            throw new MyNotFoundException("The role does'nt  exist", new Throwable().getCause());
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserBean updateUser(UserBean user, Long id) {
        if (user.getId() != id) {
            throw new MyIdMismatchException("Id MisMatch",new Throwable());
        }
        return userRepository.save(user);
    }
}
