package project.assistant.Repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.assistant.Model.Beans.FileModuleBean;
import project.assistant.Model.Beans.UserBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public interface IFileModuleRepository extends CrudRepository<FileModuleBean, Long> {

    Boolean existsFileModuleBeanByFilePath(String filePath);

    Set<FileModuleBean> findFileModuleBeanByUserBean(UserBean userBean);

}
