package Level_Test.Word.DBmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DBmanagerI {

    Connection ConnectDB();
    void InsertintoWfile(String name, int CharCount, String CreationDate, int LineCount, int PageCount,
                         int ParaCount, List<String> Strucure, String DocContent) throws SQLException;
}
