package project.assistant.MvcController;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import project.assistant.Model.Beans.FileModuleBean;
import project.assistant.Model.Beans.UserBean;
import project.assistant.Model.ViewBeans.FileMouduleViewBean;
import project.assistant.Model.ViewBeans.UserViewBean;
import project.assistant.Services.Service.FileModuleService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Controller
@RequestMapping(value = "Patient")
public class PatientControler {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileModuleService fileModuleService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        UserViewBean pateint = (UserViewBean) session.getAttribute("patient");
        if (pateint == null) {
            request.getRequestDispatcher("/api/user/login").forward(request, response);
        }

        Set<UserBean> friends = pateint.getFriends();
        ArrayList<FileModuleBean> fileModuleBeanArrayList = loadPatientFiles(friends, pateint);
        ModelAndView model = new ModelAndView("patientView/patient");
        model.addObject("fileModuleBeanArrayList", fileModuleBeanArrayList);

        return model;
    }

    public ArrayList<FileModuleBean> loadPatientFiles(Set<UserBean> friends, UserViewBean userViewBean) {

        UserBean userBean = convertToBean(userViewBean);

        Set<FileModuleBean> patientFiles = fileModuleService.getFilesByUserBean(userBean);

        Set<FileModuleBean> friendFiles = fileModuleService.getFilesByFriends(friends);

        patientFiles.addAll(friendFiles);

        ArrayList<FileModuleBean> fileModuleBeanArrayLis = new ArrayList<FileModuleBean>();

        patientFiles.iterator().forEachRemaining(fileModuleBean -> fileModuleBeanArrayLis.add(fileModuleBean));

        Collections.sort(fileModuleBeanArrayLis, (o1, o2) -> o1.getCreateDate().compareTo(o2.getCreateDate()));

        return fileModuleBeanArrayLis;
    }


    private UserBean convertToBean(UserViewBean userViewBean) {
        UserBean userBean = modelMapper.map(userViewBean, UserBean.class);
        return userBean;
    }


}
