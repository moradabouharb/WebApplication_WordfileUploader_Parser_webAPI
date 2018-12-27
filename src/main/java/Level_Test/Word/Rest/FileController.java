package Level_Test.Word.Rest;

import Level_Test.Word.Model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/file")
@CrossOrigin
public class FileController {

    @Autowired
    FileControllerDao F;

    //http://localhost:8080/api/file?all
    @RequestMapping(params = "all", method = RequestMethod.GET, produces = "application/json")
    public List<FileModel> GetAllFiles(){
        return F.findAll();
    }

}
