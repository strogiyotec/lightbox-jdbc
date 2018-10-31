package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.KeyedQuery;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.KeyedUpdate;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Update;
import com.github.strogiyotec.lightbox.jdbc.value.data.StringValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;


public final class InsertTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE testInsert (",
                        "id serial primary key ,",
                        "name text not null )"
                ),
                String.join(
                        " ",
                        "INSERT INTO testInsert ",
                        "(name) ",
                        "VALUES ",
                        "('Almas')"
                ),
                String.join(
                        " ",
                        "INSERT INTO testInsert ",
                        "(name) ",
                        "VALUES ",
                        "('Murat')"
                )
        ).execute();
    }

    @AfterClass
    public static void deleteTable() throws Exception {
        new SqlScript(
                postgres,
                "DROP TABLE testInsert"
        ).execute();
    }


    @Test
    public void testInsert() throws Exception {
        final Update insert = new Update(
                postgres,
                new SimpleQuery(
                        String.join("\n",
                                "insert into testInsert",
                                "(name)",
                                "values",
                                "(:name)"),
                        new StringValue("name", "Almas")
                )
        );
        final Integer res = insert.result().call();
        assertTrue(res == 1);
    }

    @Test
    public void insertWithKeys() throws Exception {
        final KeyedUpdate insert = new KeyedUpdate(
                postgres,
                new KeyedQuery(
                        String.join("\n",
                                "insert into testInsert",
                                "(name)",
                                "values",
                                "(:name)"),
                        new StringValue("name", "Almas")
                )
        );
        final Iterator<Map<String, Object>> iterator = insert.result().call().iterator();

        assertTrue(iterator.hasNext());

        final Map<String, Object> next = iterator.next();

        assertThat(next.get("name"), is("Almas"));
    }
}
