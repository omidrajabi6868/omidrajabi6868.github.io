package project.assistant.Repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.assistant.Model.Beans.UserBean;


@Repository
public interface IUserRepository extends CrudRepository<UserBean, Long> {

    UserBean findByUsername(String username);
}

