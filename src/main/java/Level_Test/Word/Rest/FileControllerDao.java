package Level_Test.Word.Rest;

import Level_Test.Word.Model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileControllerDao extends JpaRepository<FileModel,Integer> {

}
