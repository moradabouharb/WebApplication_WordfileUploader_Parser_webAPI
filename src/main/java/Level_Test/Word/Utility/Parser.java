package Level_Test.Word.Utility;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private BodyContentHandler handler;
    private Metadata metadata;

    public Parser(BodyContentHandler handler, Metadata metadata) {
        this.handler = handler;
        this.metadata = metadata;
    }

    public BodyContentHandler Gethandler() {
        return handler;
    }

    public Metadata GetMetadata() {
        return metadata;
    }

    public void parsing(File f) throws IOException, SAXException, TikaException {
        ParseContext pcontext = new ParseContext();
        OOXMLParser  msofficeparser = new OOXMLParser();
        FileInputStream inputstream = new FileInputStream(f);
        msofficeparser.parse(inputstream, handler, metadata, pcontext);
        inputstream.close();
    }

    public static String GetDocStrucure(File F) {
        List<Integer> titres = new ArrayList<Integer>();
        List<String> Sections = new ArrayList<String>();
        List<String> Currents = new ArrayList<String>();
        String Structure = "";
        try {
            FileInputStream fis = new FileInputStream(F);
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            List<XWPFParagraph> paragraphList = xdoc.getParagraphs();
            for (XWPFParagraph paragraph : paragraphList) {
                if (paragraph.getStyle() != null && paragraph.getStyleID().contains("Titre")) {
                    String string = paragraph.getStyle();
                    String pattern = "[0-9]+";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(string);
                    if(m.find()) {
                        titres.add(Integer.parseInt(m.group(0)));
                        Sections.add(paragraph.getText());
                    }
                }
            }
            int levels = Collections.max(titres);
            int[] Current = new int[levels];
            Arrays.fill(Current,0);
            int last_t = -1;
            for(int t : titres) {
                Current[t-1] = Current[t-1] + 1;
                if(t < last_t && last_t != 1) {
                    for(int i=t ; i< levels ; i++){
                        Current[i] = 0;
                    }
                }
                last_t = t;
                Currents.add(Arrays.toString(Current));
            }
            for(int i = 0 ; i<Currents.size() ; i++) {
                Structure += Currents.get(i) + " " + Sections.get(i) +  ",";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Structure;
    }
}
