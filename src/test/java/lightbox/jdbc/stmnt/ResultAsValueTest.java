package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.ResultAsValue;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.value.data.IntValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public final class ResultAsValueTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE valueTest (",
                        "id serial primary key ,",
                        "name text not null )"
                ),
                String.join(
                        " ",
                        "INSERT INTO valueTest ",
                        "(name) ",
                        "VALUES ",
                        "('Almas')"
                ),
                String.join(
                        " ",
                        "INSERT INTO valueTest ",
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
                "DROP TABLE valueTest"
        ).execute();
    }

    @Test(expected = IllegalStateException.class)
    public void throwExceptionOnEmptyResultSet() throws Exception {
        final ResultAsValue<String> id = new ResultAsValue<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from valueTest where id = :id ",
                                new IntValue("id", -321)
                        )
                ), String.class

        );
        final Result<String> result = id.result();
        assertTrue(result.get() != null);
    }

    @Test
    public void shouldGetSingleResult() throws Exception {
        final ResultAsValue<Integer> id = new ResultAsValue<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from valueTest where id = :id ",
                                new IntValue("id", 1)
                        )
                ), Integer.class

        );
        final Result<Integer> result = id.result();
        assertTrue(result.get() == 1);
    }

    @Test(expected = ClassCastException.class)
    public void throwExceptionOnIllegalCast() throws Exception {
        final ResultAsValue<String> id = new ResultAsValue<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from valueTest where id = :id ",
                                new IntValue("id", 1)
                        )
                ), String.class

        );
        final Result<String> result = id.result();
        assertTrue(result.get() != null);
    }
}
