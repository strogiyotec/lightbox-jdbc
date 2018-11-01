package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.session.TransactedSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Transaction;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Update;
import com.github.strogiyotec.lightbox.jdbc.value.data.StringValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

@Ignore
public final class TransactionTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE testTransaction ",
                        "(",
                        "id serial primary key ,",
                        "name text not null ,",
                        "login text unique ",
                        ");"
                )
        ).execute();
    }

    @AfterClass
    public static void deleteTable() throws Exception {
        new SqlScript(
                postgres,
                "DROP TABLE testTransaction"
        ).execute();
    }

    @Test()
    public void transaction() throws Exception {
        final TransactedSession session = new TransactedSession(postgres);
        try {
            new Transaction<>(
                    session,
                    () -> {
                        new Update(
                                session,
                                new SimpleQuery(
                                        "insert into testTransaction (name,login) values (:name,:login)",
                                        new StringValue("name", "Almas"),
                                        new StringValue("login", "almas")
                                )
                        ).result();
                        new Update(
                                session,
                                new SimpleQuery(
                                        "insert into testTransaction (name,login) values (:name,:login)",
                                        new StringValue("name", "Almas"),
                                        new StringValue("login", "almas")
                                )
                        ).result();
                        return true;
                    }
            ).result();
        } catch (final Exception exc) {
            final Iterator<Map<String, Object>> iterator = new Select(
                    postgres,
                    new SimpleQuery(
                            String.join(
                                    " ",
                                    "SELECT * from testTransaction",
                                    "where login = :login"
                            ),
                            new StringValue("login", "Almas")
                    )
            ).result().call().iterator();

            assertThat(iterator.hasNext(), is(false));

        }
    }

    @Test()
    public void transactionWithoutRollBack() throws Exception {
        final TransactedSession session = new TransactedSession(postgres);
        try {
            new Transaction<>(
                    Collections.singletonList(IllegalStateException.class),
                    session,
                    () -> {
                        new Update(
                                session,
                                new SimpleQuery(
                                        "insert into testTransaction (name,login) values (:name,:login)",
                                        new StringValue("name", "Marat"),
                                        new StringValue("login", "Marat")
                                )
                        ).result();
                        new Update(
                                session,
                                new SimpleQuery(
                                        "insert into testTransaction (name,login) values (:name,:login)",
                                        new StringValue("name", "Marat"),
                                        new StringValue("login", "marat")
                                )
                        ).result();
                        return true;
                    }
            ).result();
        } catch (final Exception exc) {
            final Iterator<Map<String, Object>> iterator = new Select(
                    postgres,
                    new SimpleQuery(
                            String.join(
                                    " ",
                                    "SELECT * from testTransaction",
                                    "where login = :login"
                            ),
                            new StringValue("login", "marat")
                    )
            ).result().call().iterator();

            assertThat(iterator.hasNext(), is(true));

        }
    }

    @Test()
    public void commitTransaction() throws Exception {
        final TransactedSession session = new TransactedSession(postgres);
        new Transaction<>(
                session,
                () -> {
                    new Update(
                            session,
                            new SimpleQuery(
                                    "insert into testTransaction (name,login) values (:name,:login)",
                                    new StringValue("name", "Sergey"),
                                    new StringValue("login", "sergey")
                            )
                    ).result();
                    return true;
                }
        ).result();
        final Iterator<Map<String, Object>> iterator = new Select(
                postgres,
                new SimpleQuery(
                        String.join(
                                " ",
                                "SELECT * from testTransaction",
                                "where login = :login"
                        ),
                        new StringValue("login", "sergey")
                )
        ).result().call().iterator();

        assertThat(iterator.hasNext(), is(true));
    }

    @Test()
    public void transactionNative() throws Exception {
        final TransactedSession session = new TransactedSession(
                new DriverSession(
                        "jdbc:postgresql://127.0.0.1:5432/test",
                        "postgres",
                        "123"
                )
        );
        final Connection connection2 = session.connection();
        connection2.setAutoCommit(false);
        try (final Connection connection = session.connection()) {
            try (final PreparedStatement st = connection.prepareStatement("INSERT INTO child (par_id) VALUES (?)")) {
                st.setInt(1, 146);
                st.execute();
            }
        }
    }
}
