package Level_Test.Word.UploadFile;

import Level_Test.Word.DBmanager.DBmanager;
import Level_Test.Word.Utility.Converter;
import Level_Test.Word.Utility.Metalist;
import Level_Test.Word.Utility.Parser;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static Level_Test.Word.Utility.Parser.GetDocStrucure;

@Controller
public class UploadFileController {

    @Autowired
    FileStorage fileStorage;
    @Autowired
    DBmanager DB;
    @Autowired
    private Converter c;
    @Autowired
    Metalist metalist;
    private BodyContentHandler handler = new BodyContentHandler();
    private Metadata metadata = new Metadata();

    @GetMapping("/")
    public String index() {
        return "/uploadform.html";
    }

    @GetMapping("/error")
    public String error() {
        return "/error.html";
    }

    @PostMapping("/uploadfile")
    public String uploadMultipartFile(MultipartFile file, Model model) throws IOException, TikaException, SAXException, SQLException {
        if(file.isEmpty()){                     // if no file was uploaded
            return "redirect:error";
        }
        try {
            fileStorage.store(file);
            model.addAttribute("message", "File uploaded successfully! -> filename = " + file.getOriginalFilename());
        } catch (Exception e) {
            model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
        }
        finally {
            File ConFile = c.convert(file);
            Parser p = new Parser(handler, metadata);
            p.parsing(ConFile);
            String Content = p.Gethandler().toString();
            Metadata meta = p.GetMetadata();
            List<String> Strucure = GetDocStrucure(ConFile);
            HashMap<String,String> metaL= metalist.GetMetaList(meta);
            //Persist data
            DB.InsertintoWfile(metaL.get("Author"),
                    Integer.parseInt(metaL.get("CHARACTER_COUNT")),
                    metaL.get("CREATION_DATE"),Integer.parseInt(metaL.get("LINE_COUNT")),
                    Integer.parseInt(metaL.get("PAGE_COUNT")),
                    Integer.parseInt(metaL.get("PARAGRAPH_COUNT")),Strucure, Content);
        }
        return "/welcome.html";
    }
}