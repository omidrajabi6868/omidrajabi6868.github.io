package project.assistant.Services.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.assistant.Model.Beans.FileDisplayForWho;
import project.assistant.Model.Beans.FileModuleBean;
import project.assistant.Model.Beans.UserBean;
import project.assistant.Repositories.IFileModuleRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileModuleService {

    @Autowired
    private IFileModuleRepository fileModuleRepository;

    public Set<FileModuleBean> getFilesByFriends(Set<UserBean> friends){


        Set<FileModuleBean> fileModuleBeans = new HashSet<FileModuleBean>();
        try {
            for (UserBean userBean: friends)
                fileModuleBeans.addAll(getFilesByUserBean(userBean));

            for (FileModuleBean  fileModuleBean: fileModuleBeans)
                if (fileModuleBean.getFileDisplayForWho()== FileDisplayForWho.Justme)
                    fileModuleBeans.remove(fileModuleBean);

             return fileModuleBeans;
        }

        catch (Exception ex){
            throw new RuntimeException(ex.getMessage(), new Throwable());
        }

    }

    public Set<FileModuleBean> getFilesByUserBean(UserBean userBean){

        try {
            Set<FileModuleBean> fileModuleBeans =
                    new HashSet<FileModuleBean>(fileModuleRepository.findFileModuleBeanByUserBean(userBean));

            return fileModuleBeans;
        }

        catch (Exception ex){
            throw new RuntimeException(ex.getMessage(), new Throwable());
        }

    }



    public FileModuleBean createFile(FileModuleBean fileModuleBean) {

        try {
            return fileModuleRepository.save(fileModuleBean);
        }

        catch (Exception ex){
            throw new RuntimeException(ex.getMessage(), new Throwable());
        }
    }

    public Boolean existThisFile(String path){
        try {
            return fileModuleRepository.existsFileModuleBeanByFilePath(path);
        }

        catch (Exception ex){
            throw new RuntimeException(ex.getMessage(), new Throwable());
        }
    }
}
