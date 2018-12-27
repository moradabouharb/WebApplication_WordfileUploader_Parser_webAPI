package Level_Test.Word.UploadFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
    void store(MultipartFile file);
}
