package project.assistant.MvcController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value ="Plate")
public class PlateController {

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String home(){
        return "plateRecognitionView/home";
    }

}
