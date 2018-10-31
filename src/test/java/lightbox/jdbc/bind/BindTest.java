package lightbox.jdbc.bind;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.bind.Column;
import com.github.strogiyotec.lightbox.jdbc.bind.RowsAsSqlObject;
import com.github.strogiyotec.lightbox.jdbc.bind.SqlObject;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.value.data.IntValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class BindTest {
    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE simpleBindTest ",
                        "(",
                        "id serial primary key ,",
                        "name text",
                        ")"
                ),
                String.join(
                        " ",
                        "INSERT INTO simpleBindTest ",
                        "(name) ",
                        "VALUES ",
                        "('Almas')"
                ),
                String.join(
                        " ",
                        "INSERT INTO simpleBindTest ",
                        "(id) ",
                        "VALUES ",
                        "(DEFAULT)"
                )
        ).execute();
    }

    @AfterClass
    public static void deleteTable() throws Exception {
        new SqlScript(
                postgres,
                "DROP TABLE simpleBindTest"
        ).execute();
    }

    @Test
    public void simpleBindTest() throws Exception {
        final User user = new RowsAsSqlObject<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "SELECT * FROM simpleBindTest where id = :id",
                                new IntValue("id", 1)
                        )
                ),
                User.class
        ).get();

        assertThat(user.name(), is("Almas"));
        assertThat(user.id(), is(1));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionOnEmptyName() throws Exception {
        final User user = new RowsAsSqlObject<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "SELECT * FROM simpleBindTest where id = :id",
                                new IntValue("id", 2)
                        )
                ),
                User.class
        ).get();

        assertThat(user.name(), is("Almas"));
        assertThat(user.id(), is(1));
    }


    interface User extends SqlObject {
        @Column(name = "name", nullable = false)
        String name();

        @Column(name = "id", nullable = false)
        int id();
    }
}
