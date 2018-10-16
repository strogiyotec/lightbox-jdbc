package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.google.common.collect.Lists;
import lightbox.jdbc.fake.FakeDatabaseSession;
import lightbox.jdbc.stmnt.select.DbUser;
import lightbox.jdbc.stmnt.select.DbUsers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

public final class SelectTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE testSelect (",
                        "id serial primary key ,",
                        "name text not null )"
                ),
                String.join(
                        " ",
                        "INSERT INTO testSelect ",
                        "(name) ",
                        "VALUES ",
                        "('Almas')"
                ),
                String.join(
                        " ",
                        "INSERT INTO testSelect ",
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
                "DROP TABLE testSelect"
        ).execute();
    }

    @Test
    public void selectAll() throws Exception {
        final Iterator<DbUser> iterator = new DbUsers(
                new Select(
                        postgres,
                        new SimpleQuery("SELECT * from testSelect")
                ).result().get()
        ).iterator();
        iterator.forEachRemaining(user -> assertTrue(!user.name().isEmpty()));
    }

    @Test
    public void selectWithNoResult() throws Exception {
        final Result<Rows> result = new Select(
                postgres,
                new SimpleQuery("SELECT * from testSelect where id = 10")
        ).result();
        assertTrue(Lists.newArrayList(result.get()).isEmpty());
    }
}
