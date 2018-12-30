package lightbox.jdbc.stmnt;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;

public final class SelectArrayTest {

    public void testArray() throws Exception {
        PreparedStatement con = null;
        Connection con = null;
        con.setArray(1,con.createArrayOf());
    }
}
