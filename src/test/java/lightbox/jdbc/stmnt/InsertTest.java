package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.KeyedQuery;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.AffectedRowsCount;
import com.github.strogiyotec.lightbox.jdbc.stmnt.GeneratedKeys;
import com.github.strogiyotec.lightbox.jdbc.value.StringValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

public final class InsertTest extends Assert {

    @Test
    public void testInsert() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final AffectedRowsCount insert = new AffectedRowsCount(
                postgres,
                new SimpleQuery(
                        String.join("\n",
                                "insert into people",
                                "(login,paasword)",
                                "values",
                                "(:name,:password)"),
                        new StringValue("name", "Almas"),
                        new StringValue("password", "qwerty"))
        );
        final Integer res = insert.result().get();
        assertTrue(res != 0);
    }

    @Test
    public void insertWithKeys() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final GeneratedKeys generatedKeys = new GeneratedKeys(
                new KeyedQuery(
                        String.join("\n",
                                "insert into people",
                                "(login,paasword)",
                                "values",
                                "(:name,:password)"),
                        new StringValue("name", "Almas"),
                        new StringValue("password", "qwerty")
                )
                , postgres
        );
        final Iterator<Map<String, Object>> iterator = generatedKeys.result().get().iterator();
        boolean keysArePresent = false;
        while (iterator.hasNext()) {
            iterator.next().forEach((k, v) -> System.out.println(k + " " + v));
            keysArePresent = true;
        }
        assertTrue(keysArePresent);
    }
}
