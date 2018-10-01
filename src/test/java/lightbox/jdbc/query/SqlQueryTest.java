package lightbox.jdbc.query;

import com.github.strogiyotec.lightbox.jdbc.query.SqlQuery;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public final class SqlQueryTest extends Assert {

    @BeforeClass
    public static void initTable() {

    }

    @Test
    public void sqlWithParams() throws Exception {
        final List<Object> argc = Arrays.asList("Almas", "Abdrazak", 21);
        final String sql = String.join(
                " ",
                "SELECT * from users where name = ? and surname = ? and age = ?"
        );
        final String sqlWithParams = new SqlQuery(argc, sql).asString();
        for (final Object o : argc) {
            assertTrue(sqlWithParams.contains(String.valueOf(o)));
        }
    }

    @Test
    public void dataValuesSql() throws Exception {
        final List<Object> argc = Arrays.asList("Almas", "Abdrazak", 21);
        final String sql = String.join(
                " ",
                "SELECT * from users where name = :name and surname = :surname and age = :age"
        );
        final String sqlWithParams = new SqlQuery(argc, sql).asString();
        for (final Object o : argc) {
            assertTrue(sqlWithParams.contains(String.valueOf(o)));
        }
    }
}
