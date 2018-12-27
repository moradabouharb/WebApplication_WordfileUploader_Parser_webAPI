package Level_Test.Word.DBmanager;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBmanagerI {

    Connection ConnectDB();
    void InsertintoWfile(String name,int CharCount,String CreationDate,int LineCount, int PageCount,
                         int ParaCount,String Strucure, String DocContent) throws SQLException;
}
