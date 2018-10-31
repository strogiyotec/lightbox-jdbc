package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.stmnt.ResultAsValues;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.value.data.IntValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;


public final class ResultAsValuesTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE valuesTest (",
                        "id serial primary key ,",
                        "name text not null )"
                ),
                String.join(
                        " ",
                        "INSERT INTO valuesTest ",
                        "(name) ",
                        "VALUES ",
                        "('Almas')"
                ),
                String.join(
                        " ",
                        "INSERT INTO valuesTest ",
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
                "DROP TABLE valuesTest"
        ).execute();
    }

    @Test
    public void testSelectAll() throws Exception {
        final ResultAsValues<Integer> id = new ResultAsValues<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from valuesTest"
                        )
                ),
                Integer.class
        );
        final List<Integer> ids = id.result().call();
        assertTrue(!ids.isEmpty());
    }

    @Test(expected = ClassCastException.class)
    public void throwClassCastException() throws Exception {
        final ResultAsValues<String> id = new ResultAsValues<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from valuesTest"
                        )
                ),
                String.class
        );
        final List<String> ids = id.result().call();
        assertTrue(!ids.isEmpty());
    }

    @Test
    public void returnEmptyList() throws Exception {
        final ResultAsValues<Integer> id = new ResultAsValues<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from valuesTest where id > :id",
                                new IntValue("id", 1000)
                        )
                ),
                Integer.class
        );
        final List<Integer> ids = id.result().call();
        assertTrue(ids.isEmpty());
    }
}
