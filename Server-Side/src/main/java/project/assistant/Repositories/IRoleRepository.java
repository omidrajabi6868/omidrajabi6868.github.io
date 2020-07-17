package project.assistant.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.assistant.Model.Beans.RoleBean;


@Repository
public interface IRoleRepository extends CrudRepository<RoleBean,Long> {

    RoleBean findByRole(String role);
}

