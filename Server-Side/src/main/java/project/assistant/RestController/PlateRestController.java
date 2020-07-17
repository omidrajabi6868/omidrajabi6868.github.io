package project.assistant.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/plate")
public class PlateRestController {

    private final Logger logger = LoggerFactory.getLogger(PlateRestController.class);

    private static String UPLOADED_FOLDER = "src/main/resources/static/Img/uploads";

    // 3.1.1 Single file upload
    @PostMapping("/recognition")
    public ResponseEntity<?> recognition(@RequestParam("uploadfile") MultipartFile uploadfile) {

        logger.debug("Single file upload!");

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            byte[] bytes = uploadfile.getBytes();
            File basePath = new File(UPLOADED_FOLDER);
            Path path = Paths.get(basePath.getCanonicalPath() + "/inputs/" + uploadfile.getOriginalFilename());
            Files.write(path, bytes);
            imageProcessing(uploadfile.getOriginalFilename());

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("../static/Img/uploads/outputs/" + uploadfile.getOriginalFilename(),
                new HttpHeaders(), HttpStatus.OK);

    }

    public void imageProcessing(String filename) {
        try {
            String cmd = "D:/DeepLearningSoftWare/PlateRecognition/PlateRecognitionCode/PlateRecognitionCode_V3/";
            String py = "predict";
            File basePath = new File(UPLOADED_FOLDER);
            String input = basePath.getCanonicalPath() + "/inputs/" + filename;
            String output = basePath.getCanonicalPath() + "/outputs/";
            String crop = basePath.getCanonicalPath() + "/crops/";
            String run = "C:/ProgramData/Anaconda3/envs/deeplearning/python.exe " + cmd + py + ".py" + " -i " + input + " -o " + output + " -cr " + crop;
            System.out.println(run);
            String path = "D:/DeepLearningSoftWare/PlateRecognition/PlateRecognitionCode/PlateRecognitionCode_V3";
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", run).directory(new File(path));
            Process p = null;
            p = builder.start();
            p.waitFor(100000, TimeUnit.SECONDS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
