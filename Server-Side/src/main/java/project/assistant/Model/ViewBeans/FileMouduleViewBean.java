package project.assistant.Model.ViewBeans;

public class FileMouduleViewBean {

    private Long id;

    private String fileName;

    private String fileText;

    public String getFileText() {
        return fileText;
    }

    public void setFileText(String fileText) {
        this.fileText = fileText;
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
}
