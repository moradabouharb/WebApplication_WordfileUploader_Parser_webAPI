package Level_Test.Word.Model;
import javax.persistence.*;

@Entity
@Table(name = "Wfile")
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String AuthorName;

    @Column
    private String CreationDate;

    @Column
    private int LineCount;

    @Column
    private int PageCount;

    @Column
    private int ParagraphCount;

    @Column
    private int CharCount;

    @Column(columnDefinition="TEXT")
    private String DocStructure;

    @Column(columnDefinition="TEXT")
    private String DocContent;

    public FileModel(){}

    public FileModel(int id, String AuthorName, String DocStructure, String CreationDate,
                     int LineCount, int PageCount, int ParagraphCount, int CharCount, String DocContent){
        this.id = id;
        this.AuthorName = AuthorName;
        this.DocStructure = DocStructure;
        this.CreationDate = CreationDate;
        this.LineCount = LineCount;
        this.PageCount = PageCount;
        this.ParagraphCount = ParagraphCount;
        this.CharCount = CharCount;
        this.DocContent = DocContent;

    }

    public int getid(){
        return id;
    }

    public void setid(int id){
        this.id = id;
    }

    public String getAuthorName(){
        return AuthorName;
    }

    public void setAuthorName(String AuthorName){
        this.AuthorName = AuthorName;
    }

    public String getDocStructure(){
        return DocStructure;
    }

    public void setDocStructure(String DocStructure){
        this.DocStructure = DocStructure;
    }

    public String getCreationDate(){
        return CreationDate;
    }

    public void setCreationDate(String CreationDate){
        this.CreationDate = CreationDate;
    }

    public int getLineCount(){
        return LineCount;
    }

    public void setLineCount(int LineCount){
        this.LineCount = LineCount;
    }

    public int getPageCount(){
        return PageCount;
    }

    public void setPageCount(int PageCount){
        this.PageCount = PageCount;
    }

    public int getParagraphCount(){
        return ParagraphCount;
    }

    public void setParagraphCount(int ParagraphCount){
        this.ParagraphCount = ParagraphCount;
    }

    public String getDocContent(){
        return DocContent;
    }

    public void setDocContent(String DocContent){
        this.DocContent = DocContent;
    }

    public int getCharCount(){
        return CharCount;
    }

    public void setCharCount(int CharCount){
        this.CharCount = CharCount;
    }

}
