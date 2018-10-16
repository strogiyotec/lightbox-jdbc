package lightbox.jdbc.log;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.log.LogStatements;
import com.github.strogiyotec.lightbox.jdbc.log.LogStatementsOf;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValuesOf;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.session.LoggedSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonArray;

@Slf4j
public final class LogSqlTest extends Assert {

    @Test
    public void shouldLogSqlQuery() throws Exception {
        final EmbeddedPostgres start = EmbeddedPostgres.start();
        final SimpleQuery query = new SimpleQuery("select * from people");
        final Session postgres = new LoggedSession(
                new DriverSession(
                        "jdbc:postgresql://127.0.0.1:5432/test",
                        "postgres",
                        "123"
                ),
                log,
                query,
                new LogStatementsOf(LogStatements.CONNECTION, LogStatements.RESULT_SET)
        );
        final Select select = new Select(postgres, query);
        final JsonArray jsonValues = new JsonValuesOf(select.result().get());
        assertTrue(jsonValues.size() != 0);
    }
}
