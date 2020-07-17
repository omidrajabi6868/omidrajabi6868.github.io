package project.assistant.Model.Beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.integration.annotation.Default;
import org.springframework.integration.annotation.Publisher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "FileModuleBean")
@Table(name = "FILE_MODULE")
public class FileModuleBean extends BaseBean {

    @Id
    @Column(name="FILE_MODULE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name="FILE_NAME")
    private String fileName;

    @Basic
    @Column(name="FILE_PATH")
    private String filePath;

    @Basic
    @Column(name="FILE_SIZE")
    private Long fileSize;

    @Basic
    @Column(name="FILE_TEXT")
    private String fileText;

    @Basic
    @ManyToOne
    private UserBean userBean;

    @Basic
    @Column(name = "File_Display_For_Who")
    private FileDisplayForWho  fileDisplayForWho;

    public FileModuleBean(){
        fileDisplayForWho= FileDisplayForWho.Public;
    }

    public FileModuleBean(UserBean userBean){
        this.userBean = userBean;
        fileDisplayForWho= FileDisplayForWho.Public;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public FileDisplayForWho getFileDisplayForWho() {
        return fileDisplayForWho;
    }

    public void setFileDisplayForWho(FileDisplayForWho fileDisplayForWho) {
        this.fileDisplayForWho = fileDisplayForWho;
    }

    public String getFileText() {
        return fileText;
    }

    public void setFileText(String fileText) {
        this.fileText = fileText;
    }

}
