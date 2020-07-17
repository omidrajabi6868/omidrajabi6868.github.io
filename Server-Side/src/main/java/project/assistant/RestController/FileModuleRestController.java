package project.assistant.RestController;

import org.hibernate.mapping.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.assistant.Model.Beans.FileModuleBean;
import project.assistant.Model.Beans.UserBean;
import project.assistant.Services.Service.FileModuleService;
import project.assistant.Services.Service.UserService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/filemodule")
public class FileModuleRestController {


    @Autowired
    private UserService userService;

    @Autowired
    private FileModuleService fileModuleService;

    @GetMapping(params = "problem")
    public String fileProblem(String problem){
        return problem;
    }

    @GetMapping("/createPost")
    public String createPost(Model model) {

        MultipartFile file = (MultipartFile) model.asMap().get("file");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String username = userDetail.getUsername();
            UserBean userBean = userService.getByUsername(username);
            FileModuleBean fileModuleBean = new FileModuleBean(userBean);
            fileModuleBean.setFilePath(model.asMap().get("filePath").toString());
            fileModuleBean.setFileName(file.getOriginalFilename());
            fileModuleBean.setFileSize(file.getSize());
            fileModuleBean.setFileText(model.asMap().get("fileText").toString());
            fileModuleService.createFile(fileModuleBean);
            return "success";
        }

        return null;

    }
}
