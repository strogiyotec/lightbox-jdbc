package lightbox.jdbc.log;

import com.github.strogiyotec.lightbox.jdbc.log.SqlQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public final class SqlQueryTest extends Assert {

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
}
