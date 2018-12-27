package Level_Test.Word.DBmanager;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class DBmanager implements DBmanagerI {

    @Override
    public Connection ConnectDB() {
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:mem:Word";
        String USER = "mmm";
        String PASS = "123";
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void InsertintoWfile(String name, int CharCount, String CreationDate, int LineCount, int PageCount,
                                int ParaCount, List<String> Structure, String DocContent)
            throws SQLException {
        PreparedStatement ps = ConnectDB().prepareStatement
                ("insert into Wfile(author_name,char_count,Creation_date" +
                        ",Line_count,page_count,paragraph_count,doc_structure,doc_Content) values(?,?,?,?,?,?,?,?)");
        try{
            ps.setString(1,name);
            ps.setInt(2,CharCount);
            ps.setString(3,CreationDate);
            ps.setInt(4,LineCount);
            ps.setInt(5,PageCount);
            ps.setInt(6,ParaCount);
            ps.setString(7, String.valueOf(Structure));
            ps.setString(8,DocContent);
            ps.executeUpdate();
        }
        finally {
            ps.close();
            ConnectDB().close();
        }
    }
}
