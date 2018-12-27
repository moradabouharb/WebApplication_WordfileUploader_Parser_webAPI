package Level_Test.Word.Utility;

import org.apache.tika.metadata.MSOffice;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Metalist {

    public HashMap<String, String> GetMetaList(Metadata meta){

        HashMap<String, String> metalist = new HashMap<String, String>();
        metalist.put("Author",meta.get(MSOffice.AUTHOR));
        metalist.put("CREATION_DATE",meta.get(MSOffice.CREATION_DATE));
        metalist.put("LINE_COUNT",meta.get(MSOffice.LINE_COUNT));
        metalist.put("PAGE_COUNT",meta.get(MSOffice.PAGE_COUNT));
        metalist.put("PARAGRAPH_COUNT",meta.get(MSOffice.PARAGRAPH_COUNT));
        metalist.put("CHARACTER_COUNT",meta.get(MSOffice.CHARACTER_COUNT));
        return metalist;
    }
}
